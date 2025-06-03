package org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "tmdb")
@Getter
@Setter
public class TmdbConfiguration {

    private String apiKey;

    private String apiUrl;
}
