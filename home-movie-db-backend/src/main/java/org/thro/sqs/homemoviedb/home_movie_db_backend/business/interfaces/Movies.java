package org.thro.sqs.homemoviedb.home_movie_db_backend.business.interfaces;

import java.util.List;

import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.MovieDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.exceptions.MovieNotFoundException;
import org.thro.sqs.homemoviedb.home_movie_db_backend.exceptions.UserNotFoundException;

public interface Movies {

    public List<MovieDTO> getAllUserMovies();

    public MovieDTO getMovieById(Long movieId);

    public MovieDTO saveMovieById(Long id) throws MovieNotFoundException, UserNotFoundException;
}
