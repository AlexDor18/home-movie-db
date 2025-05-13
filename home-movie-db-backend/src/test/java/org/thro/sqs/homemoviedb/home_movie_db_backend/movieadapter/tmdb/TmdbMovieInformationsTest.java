package org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb;

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
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.MovieDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb.mapper.TmdbMapper;
import org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb.models.TmdbMovieListMessage;
import org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb.models.TmdbMovieMessage;

@ExtendWith(MockitoExtension.class)
class TmdbMovieInformationsTest {

    @Mock
    private TmdbHttpClient tmdbHttpClientMock;

    @Spy
    private TmdbMapper tmdbMapper = Mappers.getMapper(TmdbMapper.class);

    @InjectMocks
    private TmdbMovieInformations sut;

    @Test
    void getMovieInformationsByIdTest() {
        Mockito.when(tmdbHttpClientMock.get("/movie/1?language=de-DE", TmdbMovieMessage.class)).thenReturn(new TmdbMovieMessage(){{
            setId(1);
            setOverview("overview");
            setTitle("title");
            setPoster_path("/thumbnailUrl");
        }});

        MovieDTO result = sut.getMovieInformationsById(1L);

        Assertions.assertEquals(1, result.getId());
        Assertions.assertEquals("overview", result.getOverview());
        Assertions.assertEquals("title", result.getTitle());
        Assertions.assertEquals("https://media.themoviedb.org/t/p/w220_and_h330_face/thumbnailUrl", result.getThumbnailUrl());
    }

    @Test
    void getSearchMovieByQueryTest(){
        Mockito.when(tmdbHttpClientMock.get("/search/movie?language=de-DE&query=query&include_adult=true", TmdbMovieListMessage.class)).thenReturn(
            new TmdbMovieListMessage(){{
                setResults(List.of(new TmdbMovieMessage(){{
                    setId(1);
                    setOverview("overview");
                    setTitle("title");
                    setPoster_path("/thumbnailUrl");
                }}));
            }}
        );

        List<MovieDTO> result = sut.searchMovieByQuery("query", true);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(1, result.get(0).getId());
        Assertions.assertEquals("overview", result.get(0).getOverview());
        Assertions.assertEquals("title", result.get(0).getTitle());
        Assertions.assertEquals("https://media.themoviedb.org/t/p/w220_and_h330_face/thumbnailUrl", result.get(0).getThumbnailUrl());
    }

}
