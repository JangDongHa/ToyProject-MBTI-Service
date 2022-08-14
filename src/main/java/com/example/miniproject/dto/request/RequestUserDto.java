package com.example.miniproject.dto.request;

import com.example.miniproject.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestUserDto {
    private String username;
    private String password;

    public User toUser(){
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }
}
