package org.thro.sqs.homemoviedb.home_movie_db_backend.dao.interfaces.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.entity.MovieEntity;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

}
