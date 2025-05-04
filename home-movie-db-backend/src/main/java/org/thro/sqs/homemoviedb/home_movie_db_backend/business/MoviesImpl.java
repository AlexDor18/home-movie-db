package org.thro.sqs.homemoviedb.home_movie_db_backend.business;

import java.util.List;

import org.springframework.stereotype.Service;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.interfaces.Movies;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.MovieDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.interfaces.dao.MovieDao;
import org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.interfaces.MovieInformations;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MoviesImpl implements Movies {

    private MovieInformations movieInformations;

    private MovieDao movieDao;

    public MoviesImpl(MovieInformations informations, MovieDao movieDaoBean) {
        movieInformations = informations;
        movieDao = movieDaoBean;
    }

    @Override
    public List<MovieDTO> getAllUserMovies() {
        return this.movieDao.getAllMoviesForUser(1L);
    }

    @Override
    public MovieDTO getMovieById(Long movieId) {
        final MovieDTO dbMovie = this.movieDao.getMovieById(movieId);

        if(dbMovie != null) {
            log.debug("Movie {} found in database", movieId);
            return dbMovie;
        }

        log.debug("Movie {} not found in database", movieId);
        return this.movieInformations.getMovieInformationsById(movieId);
    }

    @Override
    public MovieDTO saveMovieById(Long movieId) {
        final MovieDTO movieToSave = this.getMovieById(movieId);
        this.movieDao.saveMovie(movieToSave);
        return this.movieDao.getMovieById(movieId);
    }
}
