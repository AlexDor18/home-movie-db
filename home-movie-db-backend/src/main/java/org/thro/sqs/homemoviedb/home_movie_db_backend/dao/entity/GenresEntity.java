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
public class GenresEntity {

    @Id
    private Long id;
    
    private String name;

    @ManyToMany(mappedBy = "genres")
    private List<MovieEntity> movies; // NOSONAR: Entity mapping done by hibernate
}
