package org.thro.sqs.homemoviedb.home_movie_db_backend.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.interfaces.PublicMovies;
import org.thro.sqs.homemoviedb.home_movie_db_backend.web.models.MovieMessage;

@WebMvcTest(MoviesWeb.class)
class MoviesWebTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PublicMovies publicMovies;

    @Test
    void allMoviesTest() throws Exception {    
        Mockito.when(publicMovies.getAllMovies()).thenReturn(List.of(new MovieMessage(){{
            setId(1L);
            setTitle("Movie 1");
            setOverview("Overview 1");
            setGenres(List.of("Genre 1", "Genre 2"));
        }}));
        this.mockMvc.perform(get("/api/movies"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1L));
    }

}
