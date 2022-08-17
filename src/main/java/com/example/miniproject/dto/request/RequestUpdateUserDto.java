package com.example.miniproject.dto.request;

import com.example.miniproject.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdateUserDto {
    private String nickname;
    private String password;
    private String pastPassword;

    public User toUser(User user){
        return User.builder()
                .id(user.getId())
                .username(user.getUsername())
                .nickname(nickname)
                .password(password)
                .createdAt(user.getCreatedAt())
                .build();
    }
}
