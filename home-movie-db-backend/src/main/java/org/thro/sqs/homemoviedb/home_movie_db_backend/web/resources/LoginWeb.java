package org.thro.sqs.homemoviedb.home_movie_db_backend.web.resources;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.interfaces.UserService;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.UserDto;
import org.thro.sqs.homemoviedb.home_movie_db_backend.exceptions.UserCouldNotBeCreatedException;
import org.thro.sqs.homemoviedb.home_movie_db_backend.web.mapper.UserMapper;
import org.thro.sqs.homemoviedb.home_movie_db_backend.web.models.UserMessage;

import lombok.extern.slf4j.Slf4j;



@RestController
@Slf4j
public class LoginWeb {

    private UserService userService;

    private UserMapper userMapper;

    public LoginWeb(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }
    
    @PostMapping("/home")
    public String homeRedirect() {
        return "Login Successfull";
    }

    @PostMapping("/auth/signup")
    public UserMessage postMethodName(@RequestBody UserMessage user) {
        UserDto newUser = this.userService.createNewUser(this.userMapper.mapToUserDto(user));
        if(newUser != null) {
            return this.userMapper.mapToUserMessage(newUser);
        }
        throw new UserCouldNotBeCreatedException("User could not be created");
    }
    
}
