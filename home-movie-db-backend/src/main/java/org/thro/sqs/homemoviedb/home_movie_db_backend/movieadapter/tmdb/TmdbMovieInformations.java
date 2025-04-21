package org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb;

import java.util.List;

import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.MovieDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.interfaces.MovieInformations;

public class TmdbMovieInformations implements MovieInformations{

    @Override
    public MovieDTO getMovieInformationsById(Long movieId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMovieInformationsById'");
    }

    @Override
    public List<MovieDTO> getMoviesInformationsById(List<Long> movieIds) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMoviesInformationsById'");
    }

}
