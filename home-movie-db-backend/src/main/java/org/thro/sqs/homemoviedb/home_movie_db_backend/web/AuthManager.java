package org.thro.sqs.homemoviedb.home_movie_db_backend.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthManager {

    private AuthManager(){}

    public static String  getUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return  authentication.getName();
    }

}
