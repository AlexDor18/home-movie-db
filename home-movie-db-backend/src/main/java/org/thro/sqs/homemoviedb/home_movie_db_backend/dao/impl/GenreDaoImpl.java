package org.thro.sqs.homemoviedb.home_movie_db_backend.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.GenreDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.entity.GenresEntity;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.interfaces.dao.GenreDao;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.interfaces.repository.GenreRepository;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.mapper.MovieDaoMapper;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class GenreDaoImpl implements GenreDao {

    private GenreRepository genreRepository;
    private MovieDaoMapper movieMapper;

    public GenreDaoImpl(GenreRepository genreRepository, MovieDaoMapper movieMapper) {
        this.genreRepository = genreRepository;
        this.movieMapper = movieMapper;
    }

    @Override
    public void saveGenre(GenreDTO genre) {
        GenresEntity genresEntity = genreRepository.findById(genre.getId()).orElse(null);

        if(genresEntity == null) {
            genreRepository.saveAndFlush(this.movieMapper.toGenreEntitiy(genre));
        }
    }

    @Override
    public void saveGenres(List<GenreDTO> genres) {
        if(genres == null || genres.isEmpty()) {
            log.warn("No genres to save, skipping.");
            return;
        }
        List<GenresEntity> genresEntities = genreRepository.findAllById(genres.stream().map(GenreDTO::getId).toList());

        List<GenreDTO> missingGenresEntities = genres.stream().filter(g -> !genresEntities.stream().map(GenresEntity::getId).toList().contains(g.getId())).toList();

        if(genresEntities.isEmpty()) {
            genreRepository.saveAllAndFlush(this.movieMapper.toGenresEntityList(missingGenresEntities));
        }
    }
}
