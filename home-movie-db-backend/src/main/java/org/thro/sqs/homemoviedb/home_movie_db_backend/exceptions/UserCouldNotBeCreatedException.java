package org.thro.sqs.homemoviedb.home_movie_db_backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserCouldNotBeCreatedException extends RuntimeException{
    public UserCouldNotBeCreatedException(String message) {
        super(message);
    }
}
