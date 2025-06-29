package org.thro.sqs.homemoviedb.home_movie_db_backend.dao.interfaces.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.entity.GenresEntity;

public interface GenreRepository extends JpaRepository<GenresEntity, Long> {

}
