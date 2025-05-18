package org.thro.sqs.homemoviedb.home_movie_db_backend.business;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.MovieDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.interfaces.dao.MovieDao;
import org.thro.sqs.homemoviedb.home_movie_db_backend.exceptions.MovieNotFoundException;
import org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.interfaces.MovieInformations;

@ExtendWith(MockitoExtension.class)
class MoviesImplTest {

    @InjectMocks
    private MoviesImpl sut;

    @Mock
    private MovieDao movieDaoMock;

    @Mock
    private MovieInformations movieInformationsMock;

    @Test
    void getAllMovies() {
        Mockito.when(this.movieDaoMock.getAllMoviesForUser(1L)).thenReturn(List.of(new MovieDTO(){{
            setId(1L);
            setOverview("unittest");
            setTitle("unittest");
            setThumbnailUrl("");
        }}));

        final List<MovieDTO> result = this.sut.getAllUserMovies("testuser");
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("unittest", result.getFirst().getTitle());
    }

    @Test
    void getMovieByIdTest(){
        Mockito.when(this.movieDaoMock.getMovieById(1L)).thenReturn(new MovieDTO(){{
            setId(1L);
            setOverview("unittest");
            setTitle("unittest");
            setThumbnailUrl("");
        }});

        final MovieDTO result = this.sut.getMovieById(1L);
        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("unittest", result.getTitle());
        Mockito.verifyNoInteractions(this.movieInformationsMock);
    }

    @Test
    void getMovieByIdWhenNotFoundInDbTest(){
        Mockito.when(this.movieDaoMock.getMovieById(1L)).thenReturn(null);
        Mockito.when(this.movieInformationsMock.getMovieInformationsById(1L)).thenReturn(new MovieDTO(){{
            setId(1L);
            setOverview("unittest");
            setTitle("unittest");
            setThumbnailUrl("");
        }});

        final MovieDTO result = this.sut.getMovieById(1L);
        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("unittest", result.getTitle());
    }

    @Test
    void saveMovieByIdTest(){
        final MovieDTO demoMovie = new MovieDTO(){{
            setId(1L);
            setOverview("unittest");
            setTitle("unittest");
            setThumbnailUrl("");
        }};

        Mockito.when(this.movieDaoMock.getMovieById(1L)).thenReturn(demoMovie).thenReturn(demoMovie);

        final MovieDTO result = this.sut.saveMovieById(1L);

        Mockito.verify(this.movieDaoMock).saveMovieForUser(demoMovie, 1L);
        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("unittest", result.getTitle());
    }

    @Test
    void saveMovieByIdNoMovieFoundException(){
        Mockito.when(this.movieDaoMock.getMovieById(1L)).thenReturn(null);

        Assertions.assertThrows(MovieNotFoundException.class, () -> this.sut.saveMovieById(1L));
    }

    @Test
    void searchMovieByQueryTest(){
        Mockito.when(this.movieInformationsMock.searchMovieByQuery("query", true)).thenReturn(List.of(new MovieDTO(){{
            setId(1L);
            setOverview("unittest");
            setTitle("unittest");
            setThumbnailUrl("");
        }}));   

        final List<MovieDTO> result = this.sut.searchMovieByQuery("query", true);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("unittest", result.get(0).getTitle());
    }
}
