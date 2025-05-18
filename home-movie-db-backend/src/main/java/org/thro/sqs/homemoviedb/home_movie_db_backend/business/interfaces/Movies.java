package org.thro.sqs.homemoviedb.home_movie_db_backend.business.interfaces;

import java.util.List;

import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.MovieDTO;

public interface Movies {

    public List<MovieDTO> getAllUserMovies(String username);

    public MovieDTO getMovieById(Long movieId);

    public MovieDTO saveMovieById(Long id);

    public List<MovieDTO> searchMovieByQuery(String query, boolean adult);
}
