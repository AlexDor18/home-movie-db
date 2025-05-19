package org.thro.sqs.homemoviedb.home_movie_db_backend.dao.mapper;

import org.mapstruct.Mapper;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.UserDto;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserDaoMapper {
    UserDto mapToUserDto(UserEntity userEntity);
}
