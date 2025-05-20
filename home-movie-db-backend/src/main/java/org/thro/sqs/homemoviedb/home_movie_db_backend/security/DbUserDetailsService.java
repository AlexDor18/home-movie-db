package org.thro.sqs.homemoviedb.home_movie_db_backend.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.entity.UserEntity;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.interfaces.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DbUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public DbUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Loading user details for username " + username);
        UserEntity user = this.userRepository.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("User with username " + username + " not found");
        }

        return User.builder()
            .username(user.getUsername())
            .password("{bcrypt}"+user.getPassword())
            .build();
    }
}
