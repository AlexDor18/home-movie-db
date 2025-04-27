package org.thro.sqs.homemoviedb.home_movie_db_backend.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class MovieEntity {

    @Id
    private Long id;
    private String title;
    private String overview;
    private String thumbnailUri;
    private boolean adult;
    private String releaseDate;
    private String originalLanguage;
    private String originalTitle; 
}
