package org.thro.sqs.homemoviedb.home_movie_db_backend.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.interfaces.PublicMovies;
import org.thro.sqs.homemoviedb.home_movie_db_backend.web.mapper.MovieMapper;
import org.thro.sqs.homemoviedb.home_movie_db_backend.web.models.MovieMessage;

@RestController
@RequestMapping(ApiConfig.BASE_PATH)
public class MoviesWeb {
    
    private PublicMovies publicMovies;
    private MovieMapper mapper;

    public MoviesWeb(PublicMovies publicMovies, MovieMapper movieMapper) {
        this.publicMovies = publicMovies;
        this.mapper = movieMapper;
    }

    @GetMapping("/movies")
    public List<MovieMessage> movies() {
        return this.mapper.mapToMovieMessage(publicMovies.getAllMovies());
    }
}
