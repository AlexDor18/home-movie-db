package org.thro.sqs.homemoviedb.home_movie_db_backend.web.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserMessage {
    private String username;
    private String password;
    private String prename;
    private String surname;
}
