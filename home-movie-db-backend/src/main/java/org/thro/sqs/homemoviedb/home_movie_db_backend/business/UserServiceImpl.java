package org.thro.sqs.homemoviedb.home_movie_db_backend.business;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.interfaces.UserService;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.UserDto;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.interfaces.dao.UserDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDto createNewUser(UserDto user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(16);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return this.userDao.createNewUser(user);
    }

    @Override
    public UserDto getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String username = authentication.getName();
        
        return this.userDao.getUserByUsername(username);
    }

}
