package org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb.models;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TmdbMovieMessage {
    private boolean adult;
    private String backdrop_path; // NOSONAR: External API Defintion
    private int budget;
    private List<TmdbGenreMessage> genres;
    private String homepage;
    private int id;
    private String imdb_id; // NOSONAR: External API Defintion
    private String original_language; // NOSONAR: External API Defintion
    private String original_title; // NOSONAR: External API Defintion
    private String overview;
    private double popularity;
    private String poster_path; // NOSONAR: External API Defintion
    private List<TmdbProductionCompanyMessage> production_companies; // NOSONAR: External API Defintion
    private List<TmdbProductionCountryMessage> production_countries; // NOSONAR: External API Defintion
    private String release_date; // NOSONAR: External API Defintion
    private int revenue;
    private int runtime;
    private List<TmdbSpokenLanguageMessage> spoken_languages; // NOSONAR: External API Defintion
    private String status;
    private String tagline;
    private String title;
    private boolean video;
    private double vote_average; // NOSONAR: External API Defintion
    private int vote_count; // NOSONAR: External API Defintion
}
