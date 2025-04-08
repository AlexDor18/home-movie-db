package org.thro.sqs.homemoviedb.home_movie_db_backend.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public record MoviesWeb() {

    @GetMapping("/movies")
    public String movies() {
        return "movies";
    }
}
