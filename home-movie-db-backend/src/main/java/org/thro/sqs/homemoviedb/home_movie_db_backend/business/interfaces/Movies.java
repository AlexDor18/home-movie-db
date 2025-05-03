package org.thro.sqs.homemoviedb.home_movie_db_backend.business.interfaces;

import java.util.List;

import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.MovieDTO;

public interface Movies {

    public List<MovieDTO> getAllMovies();

    public MovieDTO getMovieById(Long movieId);
}
