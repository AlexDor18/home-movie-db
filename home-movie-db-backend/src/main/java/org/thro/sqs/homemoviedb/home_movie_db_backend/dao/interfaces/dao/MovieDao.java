package org.thro.sqs.homemoviedb.home_movie_db_backend.dao.interfaces.dao;

import java.util.List;

import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.MovieDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.exceptions.UserNotFoundException;

public interface MovieDao {

    MovieDTO getMovieById(Long id);

    void saveMovieForUser(MovieDTO movie, Long userId) throws UserNotFoundException;

    List<MovieDTO> getAllMoviesForUser(Long userId);
}