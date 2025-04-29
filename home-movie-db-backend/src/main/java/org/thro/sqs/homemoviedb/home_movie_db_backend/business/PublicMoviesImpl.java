package org.thro.sqs.homemoviedb.home_movie_db_backend.business;

import java.util.List;

import org.springframework.stereotype.Service;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.interfaces.Movies;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.MovieDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.interfaces.MovieInformations;

@Service
public class PublicMoviesImpl implements Movies {

    private MovieInformations movieInformations;

    public PublicMoviesImpl(MovieInformations informations){
        movieInformations = informations;
    }

    @Override
    public List<MovieDTO> getAllMovies() {
        MovieDTO result =  this.movieInformations.getMovieInformationsById(974576L);

        return List.of(result);
    }
}
