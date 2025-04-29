package org.thro.sqs.homemoviedb.home_movie_db_backend.dao.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserEntity {

    @Id
    private Long id;
    
    private String username;
    
    private String password;
    
    private String prename;
    
    private String surname;

    @ManyToMany
    private List<MovieEntity> movies;
}
