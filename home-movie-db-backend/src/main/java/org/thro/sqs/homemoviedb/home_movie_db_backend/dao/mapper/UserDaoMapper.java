package org.thro.sqs.homemoviedb.home_movie_db_backend.dao.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.UserDto;
import org.thro.sqs.homemoviedb.home_movie_db_backend.dao.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserDaoMapper {

    @Mapping(target = "password", ignore = true)
    UserDto mapToUserDtoWithoutPassword(UserEntity userEntity);

    @Mapping(target="movies", ignore = true)
    UserEntity mapToUserEntity(UserDto userDto);
}
