package org.thro.sqs.homemoviedb.home_movie_db_backend.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.entity.UserEntity;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.interfaces.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceTest {

    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private DbUserDetailsService sut;

    @Test
    void loadUserByUsernameTest() {
        Mockito.when(this.userRepositoryMock.findByUsername("testuser")).thenReturn(new UserEntity(){{
            setId(1L);
            setUsername("testuser");
            setPassword("password");
        }});

        UserDetails user = this.sut.loadUserByUsername("testuser");
    
        Assertions.assertEquals("testuser", user.getUsername());
        Assertions.assertEquals("{noop}password", user.getPassword());
    }

    @Test
    void loadUserByUsernameNotFoundTest() {
        Mockito.when(this.userRepositoryMock.findByUsername("testuser")).thenReturn(null);

        Assertions.assertThrows(UsernameNotFoundException.class, () -> this.sut.loadUserByUsername("testuser"));
    }

}
