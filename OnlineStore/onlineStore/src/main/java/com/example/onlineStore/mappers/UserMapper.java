package com.example.onlineStore.mappers;

import com.example.onlineStore.dto.UserDto;
import com.example.onlineStore.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "email", source = "dto.email")
    @Mapping(target = "isAdmin", source = "dto.isAdmin")
    @Mapping(target = "roles", ignore = true)
    User convertDtoToEntity(UserDto dto);
}
