package org.thro.sqs.homemoviedb.home_movie_db_backend.business;

import java.util.List;

import org.springframework.stereotype.Service;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.interfaces.Movies;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.MovieDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.UserDto;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.interfaces.dao.MovieDao;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.interfaces.dao.UserDao;
import org.thro.sqs.homemoviedb.home_movie_db_backend.exceptions.MovieNotFoundException;
import org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.interfaces.MovieInformations;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MoviesImpl implements Movies {

    private MovieInformations movieInformations;
    private MovieDao movieDao;
    private UserDao userDao;

    public MoviesImpl(MovieInformations informations, MovieDao movieDaoBean, UserDao userDaoBean) {
        movieInformations = informations;
        movieDao = movieDaoBean;
        userDao = userDaoBean;
    }

    @Override
    public List<MovieDTO> getAllUserMovies(String username) {
        UserDto user = userDao.getUserByUsername(username);

        return this.movieDao.getAllMoviesForUser(user.getId());
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

        log.info("Movie {} retived with value {}!", movieId, movieToSave);

        if(movieToSave == null) {
            log.warn("Movie {} not found!", movieId);
            throw new MovieNotFoundException("Movie with id " + movieId + " not found");
        }

        this.movieDao.saveMovieForUser(movieToSave, 1L);
        return this.movieDao.getMovieById(movieId);
    }

    @Override
    public List<MovieDTO> searchMovieByQuery(String query, boolean adult) {
        return this.movieInformations.searchMovieByQuery(query, adult);
    }
}
