package org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.MovieDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb.models.TmdbMovieMessage;

@Mapper(componentModel = "spring")
public interface TmdbMapper {

    @Mapping(source = "poster_path", target="thumbnailUrl")
    MovieDTO mapToMovieDTO(TmdbMovieMessage tmdbMovieMessage);
}
