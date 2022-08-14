package com.example.miniproject.controller;


import com.example.miniproject.dto.request.RequestUserDto;
import com.example.miniproject.dto.response.ResponseDto;
import com.example.miniproject.service.UserService;
import com.example.miniproject.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserServiceImpl userService;

    @PostMapping("/api/user/register")
    public ResponseDto<Boolean> joinUser(@RequestBody RequestUserDto dto){
        return new ResponseDto<>(HttpStatus.OK, userService.registerUser(dto));
    }
}