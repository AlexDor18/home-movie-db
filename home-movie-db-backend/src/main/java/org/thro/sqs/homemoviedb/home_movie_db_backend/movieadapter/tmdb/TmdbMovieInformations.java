package org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb;

import java.util.List;

import org.springframework.stereotype.Service;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.MovieDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.interfaces.MovieInformations;
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
        TmdbMovieMessage result = this.tmdbHttpClient.get("/movie/"+movieId, TmdbMovieMessage.class);
        return this.tmdbMapper.mapToMovieDTO(result);
    }

    @Override
    public List<MovieDTO> getMoviesInformationsById(List<Long> movieIds) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMoviesInformationsById'");
    }

}
