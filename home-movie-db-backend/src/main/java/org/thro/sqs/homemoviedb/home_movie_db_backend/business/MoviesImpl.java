package org.thro.sqs.homemoviedb.home_movie_db_backend.business;

import java.util.List;

import org.springframework.stereotype.Service;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.interfaces.Movies;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.interfaces.UserService;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.MovieDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.UserDto;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.interfaces.dao.MovieDao;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.interfaces.dao.UserDao;
import org.thro.sqs.homemoviedb.home_movie_db_backend.exceptions.MovieNotFoundException;
import org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.interfaces.MovieAdapter;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MoviesImpl implements Movies {

    private MovieAdapter movieInformations;
    private MovieDao movieDao;
    private UserDao userDao;
    private UserService userService;

    public MoviesImpl(MovieAdapter informations, MovieDao movieDaoBean, UserDao userDaoBean, UserService userService) {
        movieInformations = informations;
        movieDao = movieDaoBean;
        userDao = userDaoBean;
        this.userService = userService;
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

        UserDto user = this.userService.getAuthenticatedUser();
        this.movieDao.saveMovieForUser(movieToSave, user.getId());
        return this.movieDao.getMovieById(movieId);
    }

    @Override
    public List<MovieDTO> searchMovieByQuery(String query, boolean adult) {
        return this.movieInformations.searchMovieByQuery(query, adult);
    }

    @Override
    public void deleteMovieById(Long id) {
        final MovieDTO movie = this.getMovieById(id);
        final UserDto user = this.userService.getAuthenticatedUser();
        
        if(movie == null) {
            throw new MovieNotFoundException("Movie with id " + id + " not found");
        }
        
        this.movieDao.deleteMovieById(id, user.getId());
    }
}
