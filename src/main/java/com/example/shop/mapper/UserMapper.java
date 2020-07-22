package com.example.shop.mapper;

import com.example.shop.domain.dao.User;
import com.example.shop.domain.dto.UserDto;

public interface UserMapper {

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);
}
