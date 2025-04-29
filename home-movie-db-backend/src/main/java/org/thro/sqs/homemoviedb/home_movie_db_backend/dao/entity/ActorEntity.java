package org.thro.sqs.homemoviedb.home_movie_db_backend.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ActorEntity {

    @Id
    private Long id;
    private String prename;
    private String surname;
}
