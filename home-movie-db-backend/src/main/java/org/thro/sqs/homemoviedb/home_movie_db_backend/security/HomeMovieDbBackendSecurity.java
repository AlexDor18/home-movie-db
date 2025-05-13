package org.thro.sqs.homemoviedb.home_movie_db_backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class HomeMovieDbBackendSecurity {
    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authorize) -> {
                authorize
				.requestMatchers("/api/**")
                .authenticated();
                authorize.anyRequest()
                .permitAll();
            })
			.httpBasic(Customizer.withDefaults());
        // ...
        
		return http.build();
	}
}
