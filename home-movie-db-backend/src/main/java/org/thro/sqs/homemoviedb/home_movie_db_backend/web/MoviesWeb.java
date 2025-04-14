package org.thro.sqs.homemoviedb.home_movie_db_backend.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.interfaces.PublicMovies;
import org.thro.sqs.homemoviedb.home_movie_db_backend.web.models.MovieMessage;

@RestController
@RequestMapping(ApiConfig.BASE_PATH)
public class MoviesWeb {

    @Autowired
    private PublicMovies publicMovies;

    @GetMapping("/movies")
    public List<MovieMessage> movies() {
        return publicMovies.getAllMovies();
    }
}
