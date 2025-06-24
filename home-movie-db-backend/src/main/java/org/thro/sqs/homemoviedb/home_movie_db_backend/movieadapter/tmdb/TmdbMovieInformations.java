package org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.stereotype.Service;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.GenreDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.MovieDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.interfaces.MovieAdapter;
import org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb.mapper.TmdbMapper;
import org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb.models.TmdbMovieListMessage;
import org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb.models.TmdbMovieMessage;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TmdbMovieInformations implements MovieAdapter{

    private TmdbHttpClient tmdbHttpClient;
    private TmdbMapper tmdbMapper;
    private TmdbGenreAdapter tmdbGenreAdapter;

    public TmdbMovieInformations(TmdbHttpClient httpClient, TmdbMapper mapper, TmdbGenreAdapter genreAdapter) {
        tmdbHttpClient = httpClient;
        tmdbMapper = mapper;
        tmdbGenreAdapter = genreAdapter;
    }

    @Override
    public MovieDTO getMovieInformationsById(Long movieId) {
        TmdbMovieMessage result = this.tmdbHttpClient.get("/movie/"+movieId+"?language=de-DE", TmdbMovieMessage.class);
        List<GenreDTO> genres = this.tmdbGenreAdapter.getAllGenres();
        return this.tmdbMapper.mapToMovieDTO(result, genres);
    }

    @Override
    public List<MovieDTO> searchMovieByQuery(String query, boolean adult) {
        String sanitizedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);

        TmdbMovieListMessage result = this.tmdbHttpClient.get("/search/movie?language=de-DE&query="+sanitizedQuery+"&include_adult="+adult, TmdbMovieListMessage.class);   

        List<GenreDTO> genres = this.tmdbGenreAdapter.getAllGenres();

        if(log.isDebugEnabled()){
            log.debug("Retrieved genres: {}", genres.stream().map(GenreDTO::getName).toList());
            log.debug("movie genre {}", result.getResults().get(0).getGenre_ids());
        }
        return this.tmdbMapper.mapToMovieDTO(result.getResults(), genres);
    }
}
