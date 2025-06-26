package org.thro.sqs.homemoviedb.home_movie_db_backend.web.resources;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.interfaces.Movies;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.MovieDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.exceptions.MovieNotFoundException;
import org.thro.sqs.homemoviedb.home_movie_db_backend.web.ApiConfig;
import org.thro.sqs.homemoviedb.home_movie_db_backend.web.AuthManager;
import org.thro.sqs.homemoviedb.home_movie_db_backend.web.mapper.MovieMapper;
import org.thro.sqs.homemoviedb.home_movie_db_backend.web.models.MovieMessage;

import lombok.extern.slf4j.Slf4j;



@RestController
@RequestMapping(ApiConfig.BASE_PATH)
@Slf4j
public class MoviesWeb {
    
    private Movies movies;
    private MovieMapper mapper;

    public MoviesWeb(Movies movies, MovieMapper movieMapper) {
        this.movies = movies;
        this.mapper = movieMapper;
    }

    @GetMapping("/movies")
    public List<MovieMessage> movies() {
        String username = AuthManager.getUsername();

        return this.mapper.mapToMovieMessage(movies.getAllUserMovies(username));
    }

    @GetMapping("/movies/{id}")
    public MovieMessage getMovieById(@PathVariable("id") String id) {
        final MovieDTO result = movies.getMovieById(Long.parseLong(id));

        if(result == null){
            throw new MovieNotFoundException("Movie with id " + id + " not found");
        }

        return this.mapper.mapToMovieMessage(result);
    }

    @PostMapping("/movies/{id}")
    public MovieMessage postMovieById(@PathVariable("id") String id) {
        final MovieDTO savedMovie =  this.movies.saveMovieById(Long.parseLong(id));
        
        return this.mapper.mapToMovieMessage(savedMovie);
    }

    @DeleteMapping("/movies/{id}")
    public void deleteMovieById(@PathVariable("id") String id) {
        final MovieDTO movie = this.movies.getMovieById(Long.parseLong(id));

        if(movie == null) {
            throw new MovieNotFoundException("Movie with id " + id + " not found");
        }
        
        this.movies.deleteMovieById(Long.parseLong(id));
    }
}
