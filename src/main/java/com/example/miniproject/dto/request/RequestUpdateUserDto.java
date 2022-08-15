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
    private String username;

    public User toUser(long id, String password, LocalDateTime createAt){
        return User.builder()
                .id(id)
                .username(username)
                .password(password)
                .createdAt(createAt)
                .build();
    }
}
