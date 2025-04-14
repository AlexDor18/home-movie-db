package org.thro.sqs.homemoviedb.home_movie_db_backend.business;

import java.util.List;

import org.springframework.stereotype.Service;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.interfaces.PublicMovies;
import org.thro.sqs.homemoviedb.home_movie_db_backend.web.models.MovieMessage;

@Service
public class PublicMoviesImpl implements PublicMovies {

    @Override
    public List<MovieMessage> getAllMovies() {
        MovieMessage movieMessage = new MovieMessage();
        movieMessage.setId(1L);
        movieMessage.setTitle("Movie 1");
        movieMessage.setOverview("Overview 1");
        movieMessage.setGenres(List.of("Genre 1", "Genre 2"));

        return List.of(movieMessage);
    }
}
