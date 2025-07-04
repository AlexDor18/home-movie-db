package org.thro.sqs.homemoviedb.home_movie_db_backend.dao.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class MovieEntity {

    @Id
    private Long id;
    
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String overview;
    
    @Column(name = "thumbnail_uri")
    private String thumbnailUri;
    
    private boolean adult;
    
    private LocalDate releaseDate;

    @Column(name = "original_language")
    private String originalLanguage;
    
    @Column(name = "original_title")
    private String originalTitle; 

    @ManyToMany
    private List<GenresEntity> genres; // NOSONAR: Entity mapping is done by hibernate

    @ManyToMany(mappedBy = "movies")
    private List<UserEntity> ownedByUser;
}
