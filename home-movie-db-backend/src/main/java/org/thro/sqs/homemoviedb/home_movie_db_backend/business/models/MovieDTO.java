package org.thro.sqs.homemoviedb.home_movie_db_backend.business.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDTO {

    private Long id;

    private String title;

    private String overview;

    private String thumbnailUrl;

    private String releaseDate;

    private String originalLanguage;

    private String originalTitle;

    private boolean adult;

}
