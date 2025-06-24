package org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TmdbGenreMessage {
    private int id;
    private String name;
}
