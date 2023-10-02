package com.vasudev.blog.services;

import com.vasudev.blog.entities.User;
import com.vasudev.blog.payloads.UserDto;

import java.util.List;

public interface UserService {
     UserDto createUser(UserDto userDto);
     UserDto updateUser(UserDto userDto,Integer userId);

     UserDto getUserById(Integer userId);
     List<UserDto> getAllUsers();

     void deleteUser(Integer userId);
}
