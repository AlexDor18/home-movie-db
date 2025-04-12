package org.thro.sqs.homemoviedb.home_movie_db_backend.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thro.sqs.homemoviedb.home_movie_db_backend.web.models.MovieMessage;

@RestController
@RequestMapping(ApiConfig.BASE_PATH)
public class MoviesWeb {

    @GetMapping("/movies")
    public List<MovieMessage> movies() {
        MovieMessage movieMessage = new MovieMessage();
        movieMessage.setId(1L);
        movieMessage.setTitle("Movie 1");
        movieMessage.setOverview("Overview 1");
        movieMessage.setGenres(List.of("Genre 1", "Genre 2"));

        return List.of(movieMessage);
    }
}
