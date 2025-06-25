package org.thro.sqs.homemoviedb.home_movie_db_backend.dao.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.GenreDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.MovieDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.entity.GenresEntity;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.entity.MovieEntity;

@Mapper(componentModel = "spring")
public interface MovieDaoMapper {

    @Mapping(target = "ownedByUser", ignore = true)
    @Mapping(target = "thumbnailUri", source = "thumbnailUrl")
    MovieEntity toMovieEntity(MovieDTO movieDTO);

    List<MovieEntity> toMovieEntityList(List<MovieDTO> movieDTOList);

    @Mapping(target = "movies", ignore = true)
    GenresEntity toGenreEntitiy(GenreDTO genresEntity);

    List<GenresEntity> toGenresEntityList(List<GenreDTO> genres);

    @Mapping(target = "thumbnailUrl", source = "thumbnailUri")
    MovieDTO toMovieDTO(MovieEntity movieEntity);

    List<MovieDTO> toMovieDTOList(List<MovieEntity> movieEntityList);

    GenreDTO toGenreDTO(GenresEntity genresEntity);

    List<GenreDTO> toGenreDTOList(List<GenresEntity> genres);
}
