package org.thro.sqs.homemoviedb.home_movie_db_backend.web.resources;

import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@Slf4j
public class LoginWeb {

    private AuthenticationManager authenticationManager;

    public LoginWeb(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest) {
        log.info("Login request: {}", loginRequest);

        	Authentication authenticationRequest =
			UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.username(), loginRequest.password());
		Authentication authenticationResponse =
			this.authenticationManager.authenticate(authenticationRequest);
        
        return authenticationResponse.isAuthenticated()
            ? ResponseEntity.ok().build()
            : ResponseEntity.status(401).build();
    }
    
    public record LoginRequest(String username, String password) {
	}
}
