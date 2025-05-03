package org.thro.sqs.homemoviedb.home_movie_db_backend.dao.impl;

import java.util.Optional;

import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.MovieDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.entity.MovieEntity;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.interfaces.dao.MovieDao;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.interfaces.repository.MovieRepository;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.mapper.MovieDaoMapper;

public class MovieDaoImpl implements MovieDao{

    private MovieRepository movieRepository;

    private MovieDaoMapper movieMapper;

    public MovieDaoImpl(MovieRepository movieRepository, MovieDaoMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
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
    public void saveMovie(MovieDTO movie) {
        MovieEntity newMovie = this.movieMapper.toMovieEntity(movie);
        movieRepository.save(newMovie);
    }
}
