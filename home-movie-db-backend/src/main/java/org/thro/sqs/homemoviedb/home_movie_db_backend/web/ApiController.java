package org.thro.sqs.homemoviedb.home_movie_db_backend.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    @GetMapping(value = "/hello")
    public String requestMethodName() {
        return "Hello World";
    }
}
