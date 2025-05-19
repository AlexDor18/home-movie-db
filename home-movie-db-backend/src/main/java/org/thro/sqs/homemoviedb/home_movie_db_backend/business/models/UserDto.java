package org.thro.sqs.homemoviedb.home_movie_db_backend.business.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String username;
    private String prename;
    private String surname;
}
