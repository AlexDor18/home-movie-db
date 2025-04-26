package org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TmdbProductionCompanyMessage {
    private int id;
    private String logo_path; // NOSONAR: External API Definition
    private String name;
    private String originCountry;
}
