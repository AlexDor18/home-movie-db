package org.thro.sqs.homemoviedb.home_movie_db_backend.exceptions;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(String message) {
        super(message);
    }
}
