package org.thro.sqs.homemoviedb.home_movie_db_backend.business;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.thro.sqs.homemoviedb.home_movie_db_backend.web.models.MovieMessage;

@SpringBootTest
public class PublicMoviesImplTest {

    @InjectMocks
    private PublicMoviesImpl sut;

    @Test
    void getAllMovies() {
        final List<MovieMessage> result = this.sut.getAllMovies();
        Assertions.assertEquals(1, result.size());
    }
}
