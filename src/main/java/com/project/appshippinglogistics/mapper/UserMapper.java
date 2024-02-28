package com.project.appshippinglogistics.mapper;

import com.project.appshippinglogistics.controller.dto.auth.SignUpDto;
import com.project.appshippinglogistics.controller.dto.UserDto;
import com.project.appshippinglogistics.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User user);

    @InheritInverseConfiguration
    User toEntity(UserDto userDto);

    @Mapping(target = "name", source = "fullName")
    User toSignUp(SignUpDto signUpDto);
}