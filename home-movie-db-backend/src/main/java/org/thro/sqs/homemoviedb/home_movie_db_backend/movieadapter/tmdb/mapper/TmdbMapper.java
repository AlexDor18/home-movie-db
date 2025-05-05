package org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.MovieDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb.models.TmdbMovieMessage;

@Mapper(componentModel = "spring")
public interface TmdbMapper {

    @Mapping(source = "poster_path", target="thumbnailUrl")
    @Mapping(source = "original_language", target="originalLanguage")
    @Mapping(source = "original_title", target="originalTitle")
    @Mapping(source = "release_date", target="releaseDate")
    MovieDTO mapToMovieDTO(TmdbMovieMessage tmdbMovieMessage);
}
