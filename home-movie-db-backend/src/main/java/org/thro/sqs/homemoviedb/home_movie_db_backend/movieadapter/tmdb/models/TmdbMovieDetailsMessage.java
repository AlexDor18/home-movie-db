package org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb.models;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TmdbMovieDetailsMessage {
    private boolean adult;
    private String backdrop_path; // NOSONAR: external API definition
    private int budget;
    private List<TmdbGenreMessage> genres;
    private String homepage;
    private int id;
    private String imdb_id; // NOSONAR: external API definition
    private List<String> origin_country; // NOSONAR: external API definition
    private String original_language; // NOSONAR: external API definition
    private String original_title; // NOSONAR: external API definition
    private String overview;
    private double popularity;
    private String poster_path; // NOSONAR: external API definition
    private List<TmdbProductionCompanyMessage> production_companies; // NOSONAR: external API definition
    private List<TmdbProductionCountryMessage> production_countries; // NOSONAR: external API definition
    private String release_date; // NOSONAR: external API definition
    private int revenue;
    private int runtime;
    private List<TmdbSpokenLanguageMessage> spoken_languages; // NOSONAR: external API definition
    private String status;
    private String tagline;
    private String title;
    private boolean video;
    private double vote_average; // NOSONAR: external API definition
    private int vote_count; // NOSONAR: external API definition
}
