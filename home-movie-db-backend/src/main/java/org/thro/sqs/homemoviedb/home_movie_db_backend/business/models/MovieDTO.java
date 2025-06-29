package org.thro.sqs.homemoviedb.home_movie_db_backend.business.models;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MovieDTO {

    private Long id;

    private String title;

    private String overview;

    private String thumbnailUrl;

    private String releaseDate;

    private String originalLanguage;

    private String originalTitle;

    private boolean adult;

    private List<GenreDTO> genres;

}
