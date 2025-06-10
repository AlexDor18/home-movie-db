package org.thro.sqs.homemoviedb.home_movie_db_backend.dao.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@NamedQuery(name = "UserEntity.findByUsername", query = "SELECT u FROM UserEntity u WHERE u.username = ?1")
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;
    
    @Column(unique = true)
    private String username;
    
    private String password;
    
    private String prename;
    
    private String surname;

    @ManyToMany
    private List<MovieEntity> movies;
}
