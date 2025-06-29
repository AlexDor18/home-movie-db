package org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb;

import java.util.List;

import org.springframework.stereotype.Service;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.GenreDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.interfaces.GenreAdapter;
import org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb.mapper.TmdbMapper;
import org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb.models.TmdbGenreListMessage;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TmdbGenreAdapter implements GenreAdapter {

    private TmdbHttpClient tmdbHttpClient;
    private TmdbMapper tmdbMapper;

    public TmdbGenreAdapter(TmdbHttpClient httpClient, TmdbMapper mapper) {
        tmdbHttpClient = httpClient;
        tmdbMapper = mapper;
    }


    @Override
    public List<GenreDTO> getAllGenres() {
        TmdbGenreListMessage result = this.tmdbHttpClient.get("/genre/movie/list?language=de-DE", TmdbGenreListMessage.class);
        if(log.isDebugEnabled()){
            log.debug("Retrieved genres: {}", result.getGenres().size());
        }
        return this.tmdbMapper.mapToGenreDTO(result.getGenres());
    }

    

}
