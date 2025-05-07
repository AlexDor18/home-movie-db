package org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TmdbHttpClient {

    private TmdbConfiguration configuration;

    private RestClient restClient;

    public TmdbHttpClient(TmdbConfiguration config) {
        configuration = config;

        log.info(config.getApiKey());

        restClient = RestClient.builder().baseUrl("https://api.themoviedb.org/3").defaultHeader("Authorization", "Bearer " + configuration.getApiKey()).defaultHeader("Content-Type", "application/json").build();
    }

    /**
     * Executes a get request to the TMDB API definied by the relative URI
     * 
     * @param uri The relative URI of the API endpoint to be called
     * @param responseClass The class of the response body
     * @return The response body
     * @throws HttpClientErrorException if the API responded with an error
     */
    public <T extends Object> T get(String uri, Class<T> responseClass) {
        try {
            return restClient.get().uri(uri).retrieve().body(responseClass);
        } catch(HttpClientErrorException err){
            log.error(uri, err);
            throw err;
        }
        
    }
}
