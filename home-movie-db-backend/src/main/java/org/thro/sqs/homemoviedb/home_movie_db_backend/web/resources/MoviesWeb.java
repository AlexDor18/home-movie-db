package org.thro.sqs.homemoviedb.home_movie_db_backend.web.resources;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.interfaces.Movies;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.MovieDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.web.ApiConfig;
import org.thro.sqs.homemoviedb.home_movie_db_backend.web.mapper.MovieMapper;
import org.thro.sqs.homemoviedb.home_movie_db_backend.web.models.MovieMessage;



@RestController
@RequestMapping(ApiConfig.BASE_PATH)
public class MoviesWeb {
    
    private Movies movies;
    private MovieMapper mapper;

    public MoviesWeb(Movies movies, MovieMapper movieMapper) {
        this.movies = movies;
        this.mapper = movieMapper;
    }

    @GetMapping("/movies")
    public List<MovieMessage> movies() {
        return this.mapper.mapToMovieMessage(movies.getAllMovies());
    }

    @GetMapping("/movies/{id}")
    public MovieMessage getMovieById(@PathVariable("id") String id) {
        return this.mapper.mapToMovieMessage(movies.getMovieById(Long.parseLong(id)));
    }

    @PostMapping("/movies/{id}")
    public MovieMessage postMovieById(@PathVariable("id") String id) {
        final MovieDTO savedMovie =  this.movies.saveMovieById(Long.parseLong(id));
        
        return this.mapper.mapToMovieMessage(savedMovie);
    }
    
}
