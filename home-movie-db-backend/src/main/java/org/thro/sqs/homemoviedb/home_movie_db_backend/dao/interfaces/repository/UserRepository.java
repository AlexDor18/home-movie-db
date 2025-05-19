package org.thro.sqs.homemoviedb.home_movie_db_backend.dao.interfaces.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
