package org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb.models;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TmdbMovieListMessage {

    private List<TmdbMovieMessage> results;
}
