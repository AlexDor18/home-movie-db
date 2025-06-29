package org.thro.sqs.homemoviedb.home_movie_db_backend.web.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.interfaces.UserService;
import org.thro.sqs.homemoviedb.home_movie_db_backend.web.ApiConfig;
import org.thro.sqs.homemoviedb.home_movie_db_backend.web.mapper.UserMapper;
import org.thro.sqs.homemoviedb.home_movie_db_backend.web.models.UserMessage;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping(ApiConfig.BASE_PATH)
public class UserWeb {

    private UserService userService;

    private UserMapper mapper;

    public UserWeb(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/user")
    public UserMessage getUser() {
        return this.mapper.mapToUserMessage(this.userService.getAuthenticatedUser());
    }
}
