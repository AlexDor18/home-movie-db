package org.thro.sqs.homemoviedb.home_movie_db_backend.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class ResourceController {
    @RequestMapping(value = "/{path:[^\\.]*}") // NOSONAR: param not required because of error handling
    public String redirect() {
        return "forward:/index.html";
    }
    
}
