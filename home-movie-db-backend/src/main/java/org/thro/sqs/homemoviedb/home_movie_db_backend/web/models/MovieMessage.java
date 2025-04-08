package org.thro.sqs.homemoviedb.home_movie_db_backend.web.models;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieMessage {
    private Long id;

    private String title;
    
    private String overview;

    private List<String> genres;
}
