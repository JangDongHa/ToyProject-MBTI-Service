package com.example.miniproject.service;

import com.example.miniproject.dto.request.RequestUpdateUserDto;
import com.example.miniproject.dto.request.RequestUserDto;
import com.example.miniproject.dto.response.UserDto;

public interface UserService {
    boolean registerUser(RequestUserDto dto);
    boolean updateUser(RequestUpdateUserDto dto, String usernameTK);
    boolean deleteUser(String usernameTK);
    UserDto getUser(String usernameTK);
}
