package org.thro.sqs.homemoviedb.home_movie_db_backend.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.MovieDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.entity.MovieEntity;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.entity.UserEntity;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.interfaces.dao.GenreDao;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.interfaces.repository.MovieRepository;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.interfaces.repository.UserRepository;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.mapper.MovieDaoMapper;
import org.thro.sqs.homemoviedb.home_movie_db_backend.exceptions.UserNotFoundException;
import org.thro.sqs.homemoviedb.home_movie_db_backend.exceptions.MovieNotFoundException;

@ExtendWith(MockitoExtension.class)
class MovieDaoImplTest {

    @InjectMocks
    private MovieDaoImpl sut;

    @Mock
    private MovieRepository movieRepositoryMock;

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private GenreDao genreDaoMock;

    @Spy
    private MovieDaoMapper movieDaoMapperMock = Mappers.getMapper(MovieDaoMapper.class);

    @Test
    void getMovieByIdTest() {
        Mockito.when(this.movieRepositoryMock.findById(12L)).thenReturn(Optional.of(new MovieEntity(){{
            setId(12L);
            setOverview("unittest");
            setTitle("unittest");
            setThumbnailUri("");
        }}));

        final MovieDTO result = this.sut.getMovieById(12L);
        Assertions.assertEquals(12L, result.getId());
        Assertions.assertEquals("unittest", result.getTitle());
    }

    @Test
    void getMovieByIdNotFoundTest() {
        Mockito.when(this.movieRepositoryMock.findById(12L)).thenReturn(Optional.empty());

        final MovieDTO result = this.sut.getMovieById(12L);
        Assertions.assertNull(result);
        Mockito.verifyNoMoreInteractions(this.movieDaoMapperMock);
    }

    @Test
    void getAllMoviesForUserTest() {
        Mockito.when(this.userRepositoryMock.findById(1L)).thenReturn(Optional.of(new UserEntity(){{
            setId(1L);
            setMovies(List.of(new MovieEntity(){{
                setId(1L);
                setOverview("unittest");
                setTitle("unittest");
                setThumbnailUri("");
            }}));
        }}));

        final List<MovieDTO> result = this.sut.getAllMoviesForUser(1L);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("unittest", result.get(0).getTitle());
    }

    @Test
    void getAllMoviesForUserNotFoundTest() {
        Mockito.when(this.userRepositoryMock.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(UserNotFoundException.class, () -> this.sut.getAllMoviesForUser(1L));
        Mockito.verifyNoMoreInteractions(this.movieDaoMapperMock);
    }

    @Test
    void saveMovieForUserTest() {
        MovieEntity movie1 = new MovieEntity(){{
            setId(123L);
            setOverview("unittest");
            setTitle("unittest");
            setThumbnailUri("");
        }};

        MovieEntity movie2 = new MovieEntity(){{
            setId(2L);
            setOverview("unittest2");
            setTitle("unittest2");
            setThumbnailUri("");
        }};

        List<MovieEntity> movies = new ArrayList<>();
        movies.add(movie1);

        UserEntity user1 = new UserEntity(){{
            setId(1L);
            setMovies(movies);
        }};

        Mockito.when(this.userRepositoryMock.findById(1L)).thenReturn(Optional.of(user1));

        Mockito.when(this.movieRepositoryMock.findById(2L)).thenReturn(Optional.of(movie2));


        this.sut.saveMovieForUser(new MovieDTO(){{
            setId(2L);
            setOverview("unittest2");
            setTitle("unittest2");
            setThumbnailUrl("");
        }}, 1L);

        Mockito.verifyNoMoreInteractions(this.movieRepositoryMock);
        Mockito.verify(this.userRepositoryMock).saveAndFlush(ArgumentMatchers.any(UserEntity.class));
    }

    @Test
    void saveMovieForUserNotFoundInDbTest() {
        MovieEntity movie1 = new MovieEntity(){{
            setId(123L);
            setOverview("unittest");
            setTitle("unittest");
            setThumbnailUri("");
        }};

        List<MovieEntity> movies = new ArrayList<>();
        movies.add(movie1);

        UserEntity user1 = new UserEntity(){{
            setId(1L);
            setMovies(movies);
        }};

        Mockito.when(this.userRepositoryMock.findById(1L)).thenReturn(Optional.of(user1));

        Mockito.when(this.movieRepositoryMock.findById(2L)).thenReturn(Optional.empty());

        this.sut.saveMovieForUser(new MovieDTO(){{
            setId(2L);
            setOverview("unittest2");
            setTitle("unittest2");
            setThumbnailUrl("");
        }}, 1L);

        Mockito.verify(this.movieRepositoryMock).saveAndFlush(ArgumentMatchers.any(MovieEntity.class));
        Mockito.verify(this.userRepositoryMock).saveAndFlush(ArgumentMatchers.any(UserEntity.class));
    }

    @Test
    void saveMovieForUserNotFoundTest() {
        Mockito.when(this.userRepositoryMock.findById(1L)).thenReturn(Optional.empty());    

        MovieDTO newMovie = new MovieDTO();

        Assertions.assertThrows(UserNotFoundException.class, 
            () -> this.sut.saveMovieForUser(newMovie, 1L)
        );
    }

    @Test
    void deleteMovieByIdTest() {
        MovieEntity movie1 = new MovieEntity(){{
            setId(123L);
            setOverview("unittest");
            setTitle("unittest");
            setThumbnailUri("");
        }};
        List<MovieEntity> movies = new ArrayList<>();
        movies.add(movie1);

        UserEntity user1 = new UserEntity(){{
            setId(1L);
            setMovies(movies);
        }};

        Mockito.when(this.userRepositoryMock.findById(1L)).thenReturn(Optional.of(user1));

        this.sut.deleteMovieById(123L, 1L);

        Assertions.assertTrue(user1.getMovies().isEmpty());
        Mockito.verify(this.userRepositoryMock).saveAndFlush(user1);
    }

    @Test
    void deleteMovieById_UserNotFound() {
        Mockito.when(this.userRepositoryMock.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(UserNotFoundException.class, () -> this.sut.deleteMovieById(123L, 1L));
    }

    @Test
    void deleteMovieById_MovieNotFoundForUser() {
        UserEntity user1 = new UserEntity(){{
            setId(1L);
            setMovies(new ArrayList<>());
        }};
        Mockito.when(this.userRepositoryMock.findById(1L)).thenReturn(Optional.of(user1));

        Assertions.assertThrows(MovieNotFoundException.class,
            () -> this.sut.deleteMovieById(123L, 1L));
    }
}
