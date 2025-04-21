package org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TmdbHttpClient {

    //@Value("${tmdb.apiKey}")
    private String tmdbApiKey;

    private RestClient restClient;

    public TmdbHttpClient(){
        tmdbApiKey = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjY2UxODEwZTIwZDE5N2ZmYTIxNTRhM2JkNjE1NDZmNiIsIm5iZiI6MTU0NTU3NDY1My44NDgsInN1YiI6IjVjMWY5OGZkOTI1MTQxNjgwNWJmZTdhMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.VKSP8ZOqfQL9L8BP8mrNJ2_uLOhvEsldqbs9ikY4kLs";
        restClient = RestClient.builder().baseUrl("https://api.themoviedb.org/3").defaultHeader("Authorization", "Bearer " + tmdbApiKey).defaultHeader("Content-Type", "application/json").build();
    }

    public <T extends Object> T get(String uri, Class<T> responseClass) {
        try {
            return restClient.get().uri(uri).retrieve().body(responseClass);
        } catch(HttpClientErrorException err){
            log.error(uri, err);
            throw err;
        }
        
    }
}
