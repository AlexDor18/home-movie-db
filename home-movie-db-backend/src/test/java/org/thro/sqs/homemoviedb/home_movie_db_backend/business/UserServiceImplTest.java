package org.thro.sqs.homemoviedb.home_movie_db_backend.business;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.UserDto;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.interfaces.dao.UserDao;
import org.thro.sqs.homemoviedb.home_movie_db_backend.exceptions.UserAlreadyExistsException;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
 
    @Mock
    private UserDao userDaoMock;

    @Mock
    private SecurityContext securityContextMock;

    @Mock
    private Authentication authenticationMock;

    @InjectMocks
    private UserServiceImpl sut;

    @Test
    void createNewUserTest() {
        UserDto user = new UserDto(){{
            setUsername("test");
            setPassword("test");
        }};

        Mockito.when(userDaoMock.createNewUser(user)).thenReturn(user);

        final UserDto result = sut.createNewUser(user);

        Assertions.assertEquals("test", result.getUsername());
        Assertions.assertNotEquals("test", result.getPassword());
    }

    @Test
    void dontAllowSameUsernameTest() {
        UserDto user = new UserDto(){{
            setUsername("test");
            setPassword("test");
        }};

        Mockito.when(userDaoMock.getUserByUsername("test")).thenReturn(new UserDto(){{
            setId(1L);
            setUsername("test");
        }});

        Assertions.assertThrows(UserAlreadyExistsException.class, () -> this.sut.createNewUser(user));
    }

    @Test
    void getAuthenticatedUserTest() {
        Mockito.mockStatic(SecurityContextHolder.class);
        Mockito.when(SecurityContextHolder.getContext()).thenReturn(securityContextMock);
        Mockito.when(securityContextMock.getAuthentication()).thenReturn(authenticationMock);
        Mockito.when(authenticationMock.getName()).thenReturn("testuser");

        Mockito.when(userDaoMock.getUserByUsername("testuser")).thenReturn(new UserDto(){{
            setId(1L);
            setUsername("testuser");
        }});

        final UserDto result = this.sut.getAuthenticatedUser();
        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("testuser", result.getUsername());
    }
}
