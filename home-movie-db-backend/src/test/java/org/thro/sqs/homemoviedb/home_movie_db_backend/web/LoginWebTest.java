package org.thro.sqs.homemoviedb.home_movie_db_backend.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.interfaces.UserService;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.UserDto;
import org.thro.sqs.homemoviedb.home_movie_db_backend.exceptions.UserCouldNotBeCreatedException;
import org.thro.sqs.homemoviedb.home_movie_db_backend.web.mapper.UserMapper;
import org.thro.sqs.homemoviedb.home_movie_db_backend.web.models.UserMessage;
import org.thro.sqs.homemoviedb.home_movie_db_backend.web.resources.LoginWeb;

@ExtendWith(MockitoExtension.class)
class LoginWebTest {

    @Mock
    private UserService userServiceMock;

    @Spy
    private UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @InjectMocks
    private LoginWeb sut;

    @Test
    void signupUserTest(){
        Mockito.when(this.userServiceMock.createNewUser(Mockito.any(UserDto.class))).thenReturn(new UserDto(){{
            setId(1L);
            setPrename("test");
            setSurname("test");
            setUsername("test");
        }});

        final UserMessage result = sut.signupUser(new UserMessage(){{
            setPrename("test");
            setSurname("test");
            setUsername("test");
            setPassword("test");
        }});
        Assertions.assertEquals("test", result.getUsername());
    }

    @Test
    void signupUserTestFailed(){
        Mockito.when(this.userServiceMock.createNewUser(Mockito.any(UserDto.class))).thenReturn(null);

        final UserMessage input = new UserMessage(){{
            setPrename("test");
            setSurname("test");
            setUsername("test");
            setPassword("test");
        }};

        Assertions.assertThrows(UserCouldNotBeCreatedException.class, () -> sut.signupUser(input));
    }

}
