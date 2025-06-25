package org.thro.sqs.homemoviedb.home_movie_db_backend.dao.interfaces.dao;

import java.util.List;

import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.GenreDTO;

public interface GenreDao {
    void saveGenre(GenreDTO genre);

    void saveGenres(List<GenreDTO> genres);
}
