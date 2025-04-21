package org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class TmdbHttpClient {

    @Value("${tmdb.apiKey}")
    private String tmdbApiKey;

    private RestClient restClient;

    private TmdbHttpClient(){
        restClient = RestClient.builder().baseUrl("https://api.themoviedb.org/3").defaultHeader("Authorization", "Bearer " + tmdbApiKey).defaultHeader("Content-Type", "application/json").build();
    
    }

    public <T extends Object> T get(String uri, Class<T> responseClass) {

        return restClient.get().uri(uri).retrieve().body(responseClass);
    }
}
