package org.thro.sqs.homemoviedb.home_movie_db_backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.result.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.hamcrest.Matchers.*;

import lombok.SneakyThrows;

@Testcontainers
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgresql = new PostgreSQLContainer<>("postgres:14.1-alpine")
        .withInitScript("integrationTestInitScript.sql");
    
    @LocalServerPort
    private Integer port;

    @Autowired
    private MockMvc mockMvc;

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
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(0)));
    }

    @Test
    @WithUserDetails("default_user")
    @SneakyThrows
    void testSearchEndpoint(){
        // Test the genre endpoint
        mockMvc.perform(MockMvcRequestBuilders.get("/api/genres").with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(0)));
    }
}
