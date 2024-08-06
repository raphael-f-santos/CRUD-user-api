package com.crudapplication.user_api.dto.mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crudapplication.user_api.dto.UserDTO;
import com.crudapplication.user_api.model.User;

@Component
public class UserMapper {

    @Autowired
    private ModelMapper modelMapper;

    // convert a user into a userDto
    public User toEntity(UserDTO dto) {

        User entity = modelMapper.map(
            dto, User.class);

        return entity;
    }

    // convert a userDto into a user
    public UserDTO toDto(User user) {
        
        UserDTO dto = modelMapper.map(
            user, UserDTO.class);

        return dto;
    }

    // convert a lists of usersDto into a user list
    public List<UserDTO> toDto(List<User> users) {

        return users.stream()
                .map(user -> toDto(user))
                .collect(Collectors.toList());
    }

    // optional UserDto
    public UserDTO toDto(Optional<User> user) {

        UserDTO dto = modelMapper.map(
            user, UserDTO.class);

        return dto;
    }
}
