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
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.interfaces.PublicMovies;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.MovieDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.web.mapper.MovieMapper;
import org.thro.sqs.homemoviedb.home_movie_db_backend.web.models.MovieMessage;

@ExtendWith(MockitoExtension.class)
class MoviesWebTest {

    @InjectMocks
    private MoviesWeb sut;

    @Mock
    private PublicMovies publicMovies;

    @Spy
    private MovieMapper movieMapper = Mappers.getMapper(MovieMapper.class);

    @Test
    void allMoviesTest() {    
        Mockito.when(publicMovies.getAllMovies()).thenReturn(List.of(new MovieDTO(){{
            setId(1L);
            setTitle("Movie 1");
            setOverview("Overview 1");
        }}));
        
        List<MovieMessage> result = sut.movies();
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Movie 1", result.get(0).getTitle());
        Assertions.assertEquals("Overview 1", result.get(0).getOverview());
    }

}
