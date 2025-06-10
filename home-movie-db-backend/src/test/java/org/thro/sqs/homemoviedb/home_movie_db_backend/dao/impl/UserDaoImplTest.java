package org.thro.sqs.homemoviedb.home_movie_db_backend.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.UserDto;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.entity.UserEntity;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.interfaces.repository.UserRepository;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.mapper.UserDaoMapper;
import org.thro.sqs.homemoviedb.home_movie_db_backend.exceptions.UserNotFoundException;

@ExtendWith(MockitoExtension.class)
class UserDaoImplTest {
    
    @Mock
    private UserRepository userRepositoryMock;
    
    @Spy
    private UserDaoMapper userMapper = Mappers.getMapper(UserDaoMapper.class);

    @InjectMocks
    private UserDaoImpl sut;

    @Test
    void getUserByUsernameTest() {
        Mockito.when(this.userRepositoryMock.findByUsername("testuser")).thenReturn(new UserEntity(){{
            setId(1L);
            setUsername("testuser");
        }});

        final UserDto result = this.sut.getUserByUsername("testuser");
        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("testuser", result.getUsername());
    }

    @Test
    void getUserByUsernameNotFoundTest() {
        Mockito.when(this.userRepositoryMock.findByUsername("testuser")).thenReturn(null);

        final UserDto result = this.sut.getUserByUsername("testuser");
        Assertions.assertNull(result);
    }

    @Test
    void createNewUserTest() {
        UserDto user = new UserDto(){{
            setUsername("test");
            setPassword("test");
        }};

        Mockito.when(this.userRepositoryMock.saveAndFlush(Mockito.any(UserEntity.class))).thenReturn(new UserEntity(){{
            setId(1L);
            setUsername("test");
        }});

        final UserDto result = this.sut.createNewUser(user);
        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("test", result.getUsername());
    }
}
