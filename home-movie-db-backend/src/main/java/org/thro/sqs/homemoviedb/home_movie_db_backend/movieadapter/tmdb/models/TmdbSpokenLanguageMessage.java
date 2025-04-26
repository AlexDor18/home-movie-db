package org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TmdbSpokenLanguageMessage {
    private String english_name; // NOSONAR: External API Definition
    private String iso_639_1; // NOSONAR: External API Defintion
    private String name;
}
