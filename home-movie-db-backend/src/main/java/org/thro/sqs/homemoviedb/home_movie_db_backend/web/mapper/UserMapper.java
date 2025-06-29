package org.thro.sqs.homemoviedb.home_movie_db_backend.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.UserDto;
import org.thro.sqs.homemoviedb.home_movie_db_backend.web.models.UserMessage;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    UserDto mapToUserDto(UserMessage user);

    @Mapping(target = "password", ignore = true)
    UserMessage mapToUserMessage(UserDto user);
}
