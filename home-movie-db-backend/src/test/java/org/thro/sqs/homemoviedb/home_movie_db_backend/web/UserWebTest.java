// UserWebTest.java
package org.thro.sqs.homemoviedb.home_movie_db_backend.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.interfaces.UserService;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.UserDto;
import org.thro.sqs.homemoviedb.home_movie_db_backend.web.mapper.UserMapper;
import org.thro.sqs.homemoviedb.home_movie_db_backend.web.models.UserMessage;
import org.thro.sqs.homemoviedb.home_movie_db_backend.web.resources.UserWeb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserWebTest {

    @Mock
    private UserService userServiceMock;

    @Spy
    private UserMapper mapperMock = Mappers.getMapper(UserMapper.class);

    @InjectMocks
    private UserWeb userWeb;

    @Test
    void testGetUser() {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setUsername("testuser");

        UserMessage userMessage = new UserMessage();
        userMessage.setUsername("testuser");

        when(userServiceMock.getAuthenticatedUser()).thenReturn(userDto);
        when(mapperMock.mapToUserMessage(userDto)).thenReturn(userMessage);

        // Act
        UserMessage result = userWeb.getUser();

        // Assert
        assertEquals(userMessage, result);
    }

    @Test
    void testGetUser_WhenUserServiceReturnsNull() {
        // Arrange
        when(userServiceMock.getAuthenticatedUser()).thenReturn(null);

        // Act and Assert
        assertEquals(null, userWeb.getUser());
    }
}
