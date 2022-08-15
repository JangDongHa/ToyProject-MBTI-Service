package com.example.miniproject.controller;


import com.example.miniproject.config.jwt.token.RequestToken;
import com.example.miniproject.config.jwt.token.ResponseToken;
import com.example.miniproject.dto.request.RequestUpdateUserDto;
import com.example.miniproject.dto.request.RequestUserDto;
import com.example.miniproject.dto.response.ResponseDto;
import com.example.miniproject.dto.response.UserDto;
import com.example.miniproject.service.UserService;
import com.example.miniproject.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserServiceImpl userService;

    // 회원 등록
    @PostMapping("/api/user/register")
    public ResponseDto<Boolean> joinUser(@RequestBody RequestUserDto dto){
        return new ResponseDto<>(HttpStatus.OK, userService.registerUser(dto));
    }

    // 회원 수정
    @PutMapping("/api/user")
    public ResponseDto<Boolean> updateUser(@RequestBody RequestUpdateUserDto dto, HttpServletRequest request, HttpServletResponse response){
        String updateUsername = dto.getUsername();
        ResponseDto<Boolean> returnValue = new ResponseDto<>(HttpStatus.OK, userService.updateUser(dto, getUsername(request)));
        updateResponseJWT(updateUsername, response);
        return returnValue;
    }

    // 회원 삭제
    @DeleteMapping("/api/user")
    public ResponseDto<Boolean> deleteUser(HttpServletRequest request){
        return new ResponseDto<>(HttpStatus.OK, userService.deleteUser(getUsername(request)));
    }

    // 회원 조회
    @GetMapping("/api/user")
    public ResponseDto<UserDto> getUser(HttpServletRequest request){
        return new ResponseDto<>(HttpStatus.OK, userService.getUser(getUsername(request)));
    }

    private String getUsername(HttpServletRequest request){
        RequestToken requestToken = new RequestToken(request);
        return requestToken.getUsername().orElseThrow();
    }

    private void updateResponseJWT(String username, HttpServletResponse response){
        ResponseToken responseToken = new ResponseToken(username);
        response.setHeader("Authorization", "Bearer " + responseToken.getAccessToken());
    }
}