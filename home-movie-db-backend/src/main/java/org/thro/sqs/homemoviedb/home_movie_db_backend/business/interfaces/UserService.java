package org.thro.sqs.homemoviedb.home_movie_db_backend.business.interfaces;

import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.UserDto;

public interface UserService {

    public UserDto createNewUser(UserDto user);
}
