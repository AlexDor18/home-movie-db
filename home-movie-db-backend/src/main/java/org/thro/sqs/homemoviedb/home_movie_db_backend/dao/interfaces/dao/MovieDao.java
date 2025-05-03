package org.thro.sqs.homemoviedb.home_movie_db_backend.dao.interfaces.dao;

import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.MovieDTO;

public interface MovieDao {

    MovieDTO getMovieById(Long id);

    void saveMovie(MovieDTO movie);
}