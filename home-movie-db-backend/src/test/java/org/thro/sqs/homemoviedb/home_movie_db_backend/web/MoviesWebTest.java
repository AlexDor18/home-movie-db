package org.thro.sqs.homemoviedb.home_movie_db_backend.web;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.interfaces.Movies;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.MovieDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.exceptions.MovieNotFoundException;
import org.thro.sqs.homemoviedb.home_movie_db_backend.web.mapper.MovieMapper;
import org.thro.sqs.homemoviedb.home_movie_db_backend.web.models.MovieMessage;
import org.thro.sqs.homemoviedb.home_movie_db_backend.web.resources.MoviesWeb;

@ExtendWith(MockitoExtension.class)
class MoviesWebTest {

    @InjectMocks
    private MoviesWeb sut;

    @Mock
    private Movies publicMovies;

    @Spy
    private MovieMapper movieMapper = Mappers.getMapper(MovieMapper.class);

    @Test
    void allMoviesTest() {   
        Mockito.mockStatic(AuthManager.class);
        Mockito.when(AuthManager.getUsername()).thenReturn("testuser");
        Mockito.when(publicMovies.getAllUserMovies("testuser")).thenReturn(List.of(new MovieDTO(){{
            setId(1L);
            setTitle("Movie 1");
            setOverview("Overview 1");
        }}));
        
        List<MovieMessage> result = sut.movies();
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Movie 1", result.get(0).getTitle());
        Assertions.assertEquals("Overview 1", result.get(0).getOverview());
    }

    @Test
    void getMovieByIdTest() {
        Mockito.when(publicMovies.getMovieById(1L)).thenReturn(new MovieDTO(){{
            setId(1L);
            setTitle("Movie 1");
            setOverview("Overview 1");
        }});

        MovieMessage result = sut.getMovieById("1");
        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("Movie 1", result.getTitle());
        Assertions.assertEquals("Overview 1", result.getOverview());
    }

    @Test
    void getMovieByIdNotFoundTest() {
        Mockito.when(publicMovies.getMovieById(1L)).thenReturn(null);

        Assertions.assertThrows(MovieNotFoundException.class, () -> sut.getMovieById("1"));
    }

    @Test
    void saveMovieByIdTest() {
        Mockito.when(publicMovies.saveMovieById(1L)).thenReturn(new MovieDTO(){{
            setId(1L);
            setTitle("Movie 1");
            setOverview("Overview 1");
        }});

        MovieMessage result = sut.postMovieById("1");
        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("Movie 1", result.getTitle());
        Assertions.assertEquals("Overview 1", result.getOverview());
    }

    @Test
    void deleteMovieByIdTest() {
        MovieDTO movie = new MovieDTO();
        movie.setId(1L);
        movie.setTitle("Movie 1");
        Mockito.when(publicMovies.getMovieById(1L)).thenReturn(movie);

        sut.deleteMovieById("1");

        Mockito.verify(publicMovies).deleteMovieById(1L);
    }

    @Test
    void deleteMovieByIdNotFoundTest() {
        Mockito.when(publicMovies.getMovieById(1L)).thenReturn(null);

        Assertions.assertThrows(MovieNotFoundException.class, () -> sut.deleteMovieById("1"));
        Mockito.verify(publicMovies, Mockito.never()).deleteMovieById(Mockito.anyLong());
    }
}
