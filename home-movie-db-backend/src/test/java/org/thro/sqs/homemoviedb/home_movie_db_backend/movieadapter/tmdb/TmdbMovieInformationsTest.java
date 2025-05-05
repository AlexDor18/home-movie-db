package org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb;

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

}
