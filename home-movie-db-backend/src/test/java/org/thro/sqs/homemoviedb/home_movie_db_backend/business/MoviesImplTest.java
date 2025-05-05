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

@ExtendWith(MockitoExtension.class)
class MoviesImplTest {

    @InjectMocks
    private MoviesImpl sut;

    @Mock
    private MovieDao movieDaoMock;

    @Test
    void getAllMovies() {
        Mockito.when(this.movieDaoMock.getAllMoviesForUser(1L)).thenReturn(List.of(new MovieDTO(){{
            setId(1L);
            setOverview("unittest");
            setTitle("unittest");
            setThumbnailUrl("");
        }}));

        final List<MovieDTO> result = this.sut.getAllUserMovies();
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("unittest", result.getFirst().getTitle());
    }
}
