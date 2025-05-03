package org.thro.sqs.homemoviedb.home_movie_db_backend.business;

import java.util.List;

import org.springframework.stereotype.Service;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.interfaces.Movies;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.MovieDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.interfaces.dao.MovieDao;
import org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.interfaces.MovieInformations;

@Service
public class MoviesImpl implements Movies {

    private MovieInformations movieInformations;

    private MovieDao movieDao;

    public MoviesImpl(MovieInformations informations, MovieDao movieDaoBean) {
        movieInformations = informations;
        movieDao = movieDaoBean;
    }

    @Override
    public List<MovieDTO> getAllMovies() {
        MovieDTO result =  this.movieInformations.getMovieInformationsById(974576L);

        return List.of(result);
    }

    @Override
    public MovieDTO getMovieById(Long movieId) {
        return this.movieInformations.getMovieInformationsById(movieId);
    }

    @Override
    public MovieDTO saveMovieById(Long movieId) {
        final MovieDTO movieToSave = this.getMovieById(movieId);
        this.movieDao.saveMovie(movieToSave);
        return this.movieDao.getMovieById(movieId);
    }
}
