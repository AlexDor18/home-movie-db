package org.thro.sqs.homemoviedb.home_movie_db_backend.dao.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.GenreDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.entity.GenresEntity;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.interfaces.repository.GenreRepository;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.mapper.MovieDaoMapper;

@ExtendWith(MockitoExtension.class)
class GenreDaoImplTest {

    @Mock
    private GenreRepository genreRepositoryMock;

    @Mock
    private MovieDaoMapper movieMapperMock;

    @InjectMocks
    private GenreDaoImpl sut;

    private GenreDTO genreDto;
    private GenresEntity genreEntity;

    @BeforeEach
    void setUp() {
        genreDto = new GenreDTO();
        genreDto.setId(1L);
        genreDto.setName("Action");

        genreEntity = new GenresEntity();
        genreEntity.setId(1L);
        genreEntity.setName("Action");
    }

    @Test
    void saveGenre_shouldNotSaveIfGenreExists() {
        Mockito.when(genreRepositoryMock.findById(1L)).thenReturn(Optional.of(genreEntity));

        sut.saveGenre(genreDto);

        Mockito.verify(genreRepositoryMock, Mockito.never()).saveAndFlush(Mockito.any());
    }

    @Test
    void saveGenre_shouldSaveIfGenreDoesNotExist() {
        Mockito.when(genreRepositoryMock.findById(1L)).thenReturn(Optional.empty());
        Mockito.when(movieMapperMock.toGenreEntitiy(genreDto)).thenReturn(genreEntity);

        sut.saveGenre(genreDto);

        Mockito.verify(genreRepositoryMock).saveAndFlush(genreEntity);
    }

    @Test
    void saveGenres_shouldSaveAllIfNoneExist() {
        GenreDTO genreDto2 = new GenreDTO();
        genreDto2.setId(2L);
        genreDto2.setName("Comedy");

        List<GenreDTO> genres = List.of(genreDto, genreDto2);

        Mockito.when(genreRepositoryMock.findAllById(List.of(1L, 2L))).thenReturn(Collections.emptyList());
        List<GenresEntity> entities = List.of(genreEntity, new GenresEntity() {{
            setId(2L);
            setName("Comedy");
        }});
        Mockito.when(movieMapperMock.toGenresEntityList(genres)).thenReturn(entities);

        sut.saveGenres(genres);

        Mockito.verify(genreRepositoryMock).saveAllAndFlush(entities);
    }

    @Test
    void saveGenres_shouldNotSaveIfAllExist() {
        List<GenreDTO> genres = List.of(genreDto);
        List<GenresEntity> entities = List.of(genreEntity);

        Mockito.when(genreRepositoryMock.findAllById(List.of(1L))).thenReturn(entities);

        sut.saveGenres(genres);

        Mockito.verify(genreRepositoryMock, Mockito.never()).saveAllAndFlush(Mockito.any());
    }
}
