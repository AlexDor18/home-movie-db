package org.thro.sqs.homemoviedb.home_movie_db_backend.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.MovieDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.entity.MovieEntity;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.entity.UserEntity;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.interfaces.dao.GenreDao;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.interfaces.dao.MovieDao;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.interfaces.repository.MovieRepository;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.interfaces.repository.UserRepository;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.mapper.MovieDaoMapper;
import org.thro.sqs.homemoviedb.home_movie_db_backend.exceptions.MovieNotFoundException;
import org.thro.sqs.homemoviedb.home_movie_db_backend.exceptions.UserNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class MovieDaoImpl implements MovieDao{

    private MovieRepository movieRepository;
    private UserRepository userRepository;
    private GenreDao genreDao;

    private MovieDaoMapper movieMapper;

    public MovieDaoImpl(MovieRepository movieRepository, UserRepository userRepository, GenreDao genreDao, MovieDaoMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.movieMapper = movieMapper;
        this.genreDao = genreDao;
    }

    @Override
    public MovieDTO getMovieById(Long id) {
        final Optional<MovieEntity> movie = movieRepository.findById(id);

        if(movie.isEmpty()) {
            return null;
        }

        return this.movieMapper.toMovieDTO(movie.get());
    }

    @Override
    public void saveMovieForUser(MovieDTO movie, Long userId) throws UserNotFoundException {
        UserEntity user = this.userRepository.findById(userId).orElse(null);

        if(user == null) {
            throw new UserNotFoundException("User with id " + userId + " not found");
        }

        genreDao.saveGenres(movie.getGenres());

        final MovieEntity dbMovie = movieRepository.findById(movie.getId()).orElse(null);
        if(dbMovie == null) {
            movieRepository.saveAndFlush(this.movieMapper.toMovieEntity(movie));
        }
        
        if(log.isDebugEnabled()){
            log.debug("movie entity {}", movie);
        }

        final List<MovieEntity> movies = user.getMovies();
        movies.add(this.movieMapper.toMovieEntity(movie));

        user.setMovies(movies);

        userRepository.saveAndFlush(user);
    }

    @Override
    public List<MovieDTO> getAllMoviesForUser(Long userId) {
        UserEntity user = userRepository.findById(userId).orElse(null);

        if(user == null) {
            throw new UserNotFoundException("User with id " + userId + " not found");
        }

        return this.movieMapper.toMovieDTOList(user.getMovies());
    }

    @Override
    public void deleteMovieById(Long id, Long userId) throws UserNotFoundException {
        UserEntity user = userRepository.findById(userId).orElse(null);

        if(user == null) {
            throw new UserNotFoundException("User with id " + userId + " not found");
        }

        final boolean removed = user.getMovies().removeIf(m -> m.getId().equals(id));

        if(!removed) {
            throw new MovieNotFoundException("Movie with id " + id + " not found for user with id " + userId);
        }

        this.userRepository.saveAndFlush(user);
    }
}
