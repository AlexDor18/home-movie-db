package org.thro.sqs.homemoviedb.home_movie_db_backend;

import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.springtest.MockServerTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.result.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb.models.TmdbMovieListMessage;
import org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb.models.TmdbMovieMessage;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockserver.model.HttpRequest.*;
import static org.mockserver.model.HttpResponse.*;
import static org.mockserver.model.Parameter.*;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Testcontainers
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = HomeMovieDbBackendApplication.class)
@ActiveProfiles(value = "test")
@MockServerTest
@Slf4j
class ApplicationIT {

    @SuppressWarnings("resource")
    @Container
    static PostgreSQLContainer<?> postgresql = new PostgreSQLContainer<>("postgres:14.1-alpine")
        .withInitScript("integrationTestInitScript.sql");
    
    @LocalServerPort
    private Integer port;

    @Autowired
    private MockMvc mockMvc;

    @Value("${tmdb.apiUrl}")
    private String mockUrl;

    private MockServerClient mockServerClient;

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresql::getJdbcUrl);
        registry.add("spring.datasource.username", postgresql::getUsername);
        registry.add("spring.datasource.password", postgresql::getPassword);
    }

    @Test
    void contextLoads() {
        // Tests in Spring Boot can start up the application
    }

    @Test
    @SneakyThrows
    void testRegisterEndpoint() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append("\"username\":\"test\",");
        builder.append("\"password\":\"test\",");
        builder.append("\"prename\":\"test\",");
        builder.append("\"surname\":\"test\"");
        builder.append("}");
        String json = builder.toString();

        // Test the register endpoint
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/signup").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @SneakyThrows
    void testLoginEndpoint() {
        // Test the login endpoint
        mockMvc.perform(MockMvcRequestBuilders.post("/login").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"test\",\"password\":\"test\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithUserDetails("default_user")
    @SneakyThrows
    void testMovieEndpoint(){
        // Test the movie endpoint
        mockMvc.perform(MockMvcRequestBuilders.get("/api/movies").with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }
 
    @Test
    @WithUserDetails("default_user")
    @SneakyThrows
    void testSearchEndpoint(){
        TmdbMovieMessage starWarsEpisodeIV = new TmdbMovieMessage();
        starWarsEpisodeIV.setAdult(false);
        starWarsEpisodeIV.setBackdrop_path("/path/to/starwars1/backdrop.jpg");
        starWarsEpisodeIV.setBudget(20000000);
        starWarsEpisodeIV.setGenres(new ArrayList<>());
        starWarsEpisodeIV.setHomepage("https://www.starwars.com");
        starWarsEpisodeIV.setId(11);
        starWarsEpisodeIV.setImdb_id("tt0076759");
        starWarsEpisodeIV.setOriginal_language("en");
        starWarsEpisodeIV.setOriginal_title("Star Wars: Episode IV - A New Hope");
        starWarsEpisodeIV.setOverview("In a galaxy far, far away...");
        starWarsEpisodeIV.setPopularity(10.0);
        starWarsEpisodeIV.setPoster_path("/path/to/starwars1/poster.jpg");
        starWarsEpisodeIV.setProduction_companies(new ArrayList<>());
        starWarsEpisodeIV.setProduction_countries(new ArrayList<>());
        starWarsEpisodeIV.setRelease_date("1977-05-25");
        starWarsEpisodeIV.setRevenue(100000000);
        starWarsEpisodeIV.setRuntime(121);
        starWarsEpisodeIV.setSpoken_languages(new ArrayList<>());
        starWarsEpisodeIV.setStatus("Released");
        starWarsEpisodeIV.setTagline("May the Force be with you.");
        starWarsEpisodeIV.setTitle("Star Wars: Episode IV - A New Hope");
        starWarsEpisodeIV.setVideo(false);
        starWarsEpisodeIV.setVote_average(8.1);
        starWarsEpisodeIV.setVote_count(1000);

        TmdbMovieMessage starWarsEpisodeV = new TmdbMovieMessage();
        starWarsEpisodeV.setAdult(false);
        starWarsEpisodeV.setBackdrop_path("/path/to/starwars2/backdrop.jpg");
        starWarsEpisodeV.setBudget(25000000);
        starWarsEpisodeV.setGenres(new ArrayList<>());
        starWarsEpisodeV.setHomepage("https://www.starwars.com");
        starWarsEpisodeV.setId(12);
        starWarsEpisodeV.setImdb_id("tt0079945");
        starWarsEpisodeV.setOriginal_language("en");
        starWarsEpisodeV.setOriginal_title("Star Wars: Episode V - The Empire Strikes Back");
        starWarsEpisodeV.setOverview("The battle between the Rebel Alliance and the Empire continues...");
        starWarsEpisodeV.setPopularity(10.0);
        starWarsEpisodeV.setPoster_path("/path/to/starwars2/poster.jpg");
        starWarsEpisodeV.setProduction_companies(new ArrayList<>());
        starWarsEpisodeV.setProduction_countries(new ArrayList<>());
        starWarsEpisodeV.setRelease_date("1980-06-20");
        starWarsEpisodeV.setRevenue(150000000);
        starWarsEpisodeV.setRuntime(124);
        starWarsEpisodeV.setSpoken_languages(new ArrayList<>());
        starWarsEpisodeV.setStatus("Released");
        starWarsEpisodeV.setTagline("The battle for the galaxy continues...");
        starWarsEpisodeV.setTitle("Star Wars: Episode V - The Empire Strikes Back");
        starWarsEpisodeV.setVideo(false);
        starWarsEpisodeV.setVote_average(8.3);
        starWarsEpisodeV.setVote_count(1200);
        starWarsEpisodeV.setAdult(false);
        
        TmdbMovieListMessage result = new TmdbMovieListMessage();
        result.setResults(Arrays.asList(starWarsEpisodeIV, starWarsEpisodeV));

        ObjectMapper objectMapper = new ObjectMapper();
        String resultString = objectMapper.writeValueAsString(result);

        mockServerClient.when(
            request()
                .withMethod("GET")
                .withPath("/search/movie")
                .withQueryStringParameters(
                    param("language", "de-DE"),
                    param("query", "StarWars"),
                    param("include_adult", "true")
                    )
        ).respond(
            response()
                .withBody(resultString)
                .withContentType(org.mockserver.model.MediaType.APPLICATION_JSON)
                .withStatusCode(200)
        );

        mockMvc.perform(MockMvcRequestBuilders.get("/api/search/movies?query=StarWars&adult=true").with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
    }

    @Test
    @WithUserDetails("default_user")
    @SneakyThrows
    void testAddMovie(){
        TmdbMovieMessage starWarsEpisodeIV = new TmdbMovieMessage();
        starWarsEpisodeIV.setAdult(false);
        starWarsEpisodeIV.setBackdrop_path("/path/to/starwars1/backdrop.jpg");
        starWarsEpisodeIV.setBudget(20000000);
        starWarsEpisodeIV.setGenres(new ArrayList<>());
        starWarsEpisodeIV.setHomepage("https://www.starwars.com");
        starWarsEpisodeIV.setId(11);
        starWarsEpisodeIV.setImdb_id("tt0076759");
        starWarsEpisodeIV.setOriginal_language("en");
        starWarsEpisodeIV.setOriginal_title("Star Wars: Episode IV - A New Hope");
        starWarsEpisodeIV.setOverview("In a galaxy far, far away...");
        starWarsEpisodeIV.setPopularity(10.0);
        starWarsEpisodeIV.setPoster_path("/path/to/starwars1/poster.jpg");
        starWarsEpisodeIV.setProduction_companies(new ArrayList<>());
        starWarsEpisodeIV.setProduction_countries(new ArrayList<>());
        starWarsEpisodeIV.setRelease_date("1977-05-25");
        starWarsEpisodeIV.setRevenue(100000000);
        starWarsEpisodeIV.setRuntime(121);
        starWarsEpisodeIV.setSpoken_languages(new ArrayList<>());
        starWarsEpisodeIV.setStatus("Released");
        starWarsEpisodeIV.setTagline("May the Force be with you.");
        starWarsEpisodeIV.setTitle("Star Wars: Episode IV - A New Hope");
        starWarsEpisodeIV.setVideo(false);
        starWarsEpisodeIV.setVote_average(8.1);
        starWarsEpisodeIV.setVote_count(1000);

        ObjectMapper objectMapper = new ObjectMapper();

        mockServerClient
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/movie/11")
                                .withQueryStringParameter("language", "de-DE")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withContentType(org.mockserver.model.MediaType.APPLICATION_JSON)
                                .withBody(objectMapper.writeValueAsString(starWarsEpisodeIV))
                );


        mockMvc.perform(MockMvcRequestBuilders.post("/api/movies/11").with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Star Wars: Episode IV - A New Hope"));
    }

}