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
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.GenreDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.MovieDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.web.mapper.MovieMapper;
import org.thro.sqs.homemoviedb.home_movie_db_backend.web.models.MovieMessage;
import org.thro.sqs.homemoviedb.home_movie_db_backend.web.resources.SearchWeb;

@ExtendWith(MockitoExtension.class)
class SearchWebTest {

    @Mock
    private Movies movies;

    @Spy
    private MovieMapper mapper = Mappers.getMapper(MovieMapper.class);

    @InjectMocks
    private SearchWeb sut;

    @Test
    void getSearchMovieTest() {
        Mockito.when(movies.searchMovieByQuery("query", true)).thenReturn(List.of(new MovieDTO(){{
            setId(1L);
            setAdult(true);
            setTitle("unittest");
            setGenres(List.of(new GenreDTO(){{
                setId(1L);
                setName("unittest");
            }}));
        }}));

        final List<MovieMessage> result = sut.getSearchMovie("query", true);

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("unittest", result.get(0).getGenres().getFirst());
    }

}
