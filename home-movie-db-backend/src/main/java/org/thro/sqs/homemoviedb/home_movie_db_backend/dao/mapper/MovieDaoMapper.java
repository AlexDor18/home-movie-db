package org.thro.sqs.homemoviedb.home_movie_db_backend.dao.mapper;

import java.util.List;

import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.MovieDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.entity.MovieEntity;

public interface MovieDaoMapper {

    MovieEntity toMovieEntity(MovieDTO movieDTO);

    List<MovieEntity> toMovieEntityList(List<MovieDTO> movieDTOList);

    MovieDTO toMovieDTO(MovieEntity movieEntity);

    List<MovieDTO> toMovieDTOList(List<MovieEntity> movieEntityList);

}
