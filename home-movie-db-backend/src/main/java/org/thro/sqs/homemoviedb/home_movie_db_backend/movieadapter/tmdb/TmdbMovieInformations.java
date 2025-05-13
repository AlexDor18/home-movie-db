package org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.stereotype.Service;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.MovieDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.interfaces.MovieInformations;
import org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb.mapper.TmdbMapper;
import org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb.models.TmdbMovieListMessage;
import org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb.models.TmdbMovieMessage;

@Service
public class TmdbMovieInformations implements MovieInformations{

    private TmdbHttpClient tmdbHttpClient;
    private TmdbMapper tmdbMapper;

    public TmdbMovieInformations(TmdbHttpClient httpClient, TmdbMapper mapper) {
        tmdbHttpClient = httpClient;
        tmdbMapper = mapper;
    }

    @Override
    public MovieDTO getMovieInformationsById(Long movieId) {
        TmdbMovieMessage result = this.tmdbHttpClient.get("/movie/"+movieId+"?language=de-DE", TmdbMovieMessage.class);
        return this.tmdbMapper.mapToMovieDTO(result);
    }

    @Override
    public List<MovieDTO> searchMovieByQuery(String query, boolean adult) {
        String sanitizedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);

        TmdbMovieListMessage result = this.tmdbHttpClient.get("/search/movie?language=de-DE&query="+sanitizedQuery+"&include_adult="+adult, TmdbMovieListMessage.class);   
        return this.tmdbMapper.mapToMovieDTO(result.getResults());
    }
}
