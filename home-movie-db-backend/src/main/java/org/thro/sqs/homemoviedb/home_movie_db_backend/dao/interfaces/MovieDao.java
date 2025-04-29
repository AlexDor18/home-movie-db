package org.thro.sqs.homemoviedb.home_movie_db_backend.dao.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.entity.MovieEntity;

public interface MovieDao extends JpaRepository<MovieEntity, Long> {

}
