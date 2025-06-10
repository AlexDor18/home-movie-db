package org.thro.sqs.homemoviedb.home_movie_db_backend.dao.impl;

import org.springframework.stereotype.Repository;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.UserDto;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.entity.UserEntity;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.interfaces.dao.UserDao;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.interfaces.repository.UserRepository;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.mapper.UserDaoMapper;

@Repository
public class UserDaoImpl implements UserDao {

    private UserRepository userRepository;
    private UserDaoMapper mapper;

    public UserDaoImpl(UserRepository userRepository, UserDaoMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public UserDto getUserByUsername(String username) {
        UserEntity user = this.userRepository.findByUsername(username);

        return mapper.mapToUserDtoWithoutPassword(user);
    }

    @Override
    public UserDto createNewUser(UserDto user) {
        final UserEntity newUser = this.mapper.mapToUserEntity(user);
        return this.mapper.mapToUserDtoWithoutPassword(this.userRepository.saveAndFlush(newUser));
    }
}
