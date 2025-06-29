package org.thro.sqs.homemoviedb.home_movie_db_backend.business;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.interfaces.UserService;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.MovieDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.UserDto;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.interfaces.dao.MovieDao;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.interfaces.dao.UserDao;
import org.thro.sqs.homemoviedb.home_movie_db_backend.exceptions.MovieNotFoundException;
import org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.interfaces.MovieAdapter;

@ExtendWith(MockitoExtension.class)
class MoviesImplTest {

    @InjectMocks
    private MoviesImpl sut;

    @Mock
    private MovieDao movieDaoMock;

    @Mock
    private MovieAdapter movieInformationsMock;

    @Mock
    private UserService userServiceMock;

    @Mock
    private UserDao userDaoMock;

    @Test
    void getAllMovies() {
        Mockito.when(this.userDaoMock.getUserByUsername("testuser")).thenReturn(new UserDto(){{
            setId(1L);
            setUsername("testuser");
        }});
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

        Mockito.when(this.userServiceMock.getAuthenticatedUser()).thenReturn(new UserDto(){{
            setId(1L);
            setUsername("testuser");
        }});
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

    @Test
    void deleteMovieById_shouldDeleteMovieForAuthenticatedUser() {
        MovieDTO demoMovie = new MovieDTO(){{
            setId(1L);
            setOverview("unittest");
            setTitle("unittest");
            setThumbnailUrl("");
        }};
        UserDto user = new UserDto(){{
            setId(42L);
            setUsername("testuser");
        }};

        Mockito.when(this.movieDaoMock.getMovieById(1L)).thenReturn(demoMovie);
        Mockito.when(this.userServiceMock.getAuthenticatedUser()).thenReturn(user);

        this.sut.deleteMovieById(1L);

        Mockito.verify(this.movieDaoMock).deleteMovieById(1L, 42L);
    }

    @Test
    void deleteMovieById_shouldThrowIfMovieNotFound() {
        Mockito.when(this.movieDaoMock.getMovieById(1L)).thenReturn(null);

        Assertions.assertThrows(MovieNotFoundException.class, () -> this.sut.deleteMovieById(1L));
        Mockito.verify(this.movieDaoMock, Mockito.never()).deleteMovieById(Mockito.anyLong(), Mockito.anyLong());
    }
}
