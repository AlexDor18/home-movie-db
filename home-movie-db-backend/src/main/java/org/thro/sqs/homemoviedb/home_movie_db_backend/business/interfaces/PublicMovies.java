package org.thro.sqs.homemoviedb.home_movie_db_backend.business.interfaces;

import java.util.List;

import org.thro.sqs.homemoviedb.home_movie_db_backend.web.models.MovieMessage;

public interface PublicMovies {

    public List<MovieMessage> getAllMovies();
}
