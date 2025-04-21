package org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TmdbProductionCountryMessage {
    private String iso_3166_1; // NOSONAR: External API Definition
    private String name;

}
