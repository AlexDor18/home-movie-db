package org.thro.sqs.homemoviedb.home_movie_db_backend.web.resources;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class LoginWeb {
    
    @PostMapping("/home")
    public String homeRedirect() {
        return "Login Successfull";
    }
}
