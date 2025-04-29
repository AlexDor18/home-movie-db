package org.thro.sqs.homemoviedb.home_movie_db_backend.dao.entity;

import jakarta.persistence.Column;
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
    
    @Column(name = "thumbnail_uri")
    private String thumbnailUri;
    
    private boolean adult;
    
    private String releaseDate;

    @Column(name = "original_language")
    private String originalLanguage;
    
    @Column(name = "original_title")
    private String originalTitle; 
}
