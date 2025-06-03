package org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.interfaces;

import java.util.List;

import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.MovieDTO;

public interface MovieAdapter {

    MovieDTO getMovieInformationsById(Long movieId);

    List<MovieDTO> searchMovieByQuery(String query, boolean adult);
}
