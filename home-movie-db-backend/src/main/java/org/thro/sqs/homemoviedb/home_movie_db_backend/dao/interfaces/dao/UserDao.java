package org.thro.sqs.homemoviedb.home_movie_db_backend.dao.interfaces.dao;

import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.UserDto;

public interface UserDao {

    UserDto getUserByUsername(String username);

    UserDto createNewUser(UserDto user);
}
