package org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.GenreDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.MovieDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb.models.TmdbGenreMessage;
import org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb.models.TmdbMovieMessage;

import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface TmdbMapper {

    @Mapping(source = "poster_path", target="thumbnailUrl", qualifiedByName = "mapToThumbnailUrl")
    @Mapping(source = "original_language", target="originalLanguage")
    @Mapping(source = "original_title", target="originalTitle")
    @Mapping(source = "release_date", target="releaseDate")
    MovieDTO mapToMovieDTO(TmdbMovieMessage tmdbMovieMessage);

    List<MovieDTO> mapToMovieDTO(List<TmdbMovieMessage> tmdbMovieMessages);

    GenreDTO mapToGenreDTO(TmdbGenreMessage tmdbMovieMessage);

    List<GenreDTO> mapToGenreDTO(List<TmdbGenreMessage> tmdbMovieMessages);

    @Named("mapToThumbnailUrl")
    default String mapToThumbnailUrl(String posterPath) {
        return "https://media.themoviedb.org/t/p/w220_and_h330_face" + posterPath;
    }

}
