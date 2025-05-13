package org.thro.sqs.homemoviedb.home_movie_db_backend.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.entity.UserEntity;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.interfaces.repository.UserRepository;

@Service
public class DbUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public DbUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = this.userRepository.findByUsername(username);

        UserDetails userDetails = User.builder()
            .username(user.getUsername())
            .password("{noop}"+user.getPassword())
            .roles("USER")
            .build();

        return userDetails;
    }

}
