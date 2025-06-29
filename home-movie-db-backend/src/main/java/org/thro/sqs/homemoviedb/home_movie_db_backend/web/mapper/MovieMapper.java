package org.thro.sqs.homemoviedb.home_movie_db_backend.web.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.GenreDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.MovieDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.web.models.MovieMessage;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieMessage mapToMovieMessage(MovieDTO movie);

    List<MovieMessage> mapToMovieMessage(List<MovieDTO> movies);

    @Mapping(target = "adult", ignore = true)
    @Mapping(target = "originalLanguage", ignore = true)
    @Mapping(target = "originalTitle", ignore = true)
    @Mapping(target = "releaseDate", ignore = true)
    @Mapping(target = "genres", ignore = true)
    MovieDTO mapToMovieDTO(MovieMessage movieMessage);

    default List<String> map(List<GenreDTO> genre){
        if(genre == null){
            return new ArrayList<>();
        }

        return genre.stream().map(GenreDTO::getName).toList();
    }

    List<MovieDTO> mapToMovieDTO(List<MovieMessage> movieMessages);
}
