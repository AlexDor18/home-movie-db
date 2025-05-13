package org.thro.sqs.homemoviedb.home_movie_db_backend.web.resources;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.thro.sqs.homemoviedb.home_movie_db_backend.web.ApiConfig;
import org.thro.sqs.homemoviedb.home_movie_db_backend.web.models.MovieMessage;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.interfaces.Movies;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.MovieDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.web.mapper.MovieMapper;

@RestController
@RequestMapping(ApiConfig.BASE_PATH)
public class SearchWeb {

    private Movies movies;

    private MovieMapper mapper;

    public SearchWeb(Movies movie, MovieMapper mapper) {
        this.movies = movie;
        this.mapper = mapper;
    }

    @GetMapping("/search/movies")
    public List<MovieMessage> getSearchMovie(@RequestParam("query") String query, @RequestParam("adult") boolean adult) {
        List<MovieDTO> foundMovies = this.movies.searchMovieByQuery(query, adult);

        return this.mapper.mapToMovieMessage(foundMovies);
    }
    

}
