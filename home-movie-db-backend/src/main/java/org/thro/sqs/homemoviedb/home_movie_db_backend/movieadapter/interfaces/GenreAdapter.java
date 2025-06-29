package org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.interfaces;

import java.util.List;

import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.GenreDTO;

public interface GenreAdapter {

    public List<GenreDTO> getAllGenres();

}
