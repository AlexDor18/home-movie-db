package org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb.models;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TmdbMovieListMessage {

    private List<TmdbMovieMessage> results;
}
