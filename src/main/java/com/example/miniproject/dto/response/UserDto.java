package com.example.miniproject.dto.response;

import com.example.miniproject.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private String username;
    private String nickname;

    public UserDto(User user){
        UserToUserDto(user);
    }
    public void UserToUserDto(User user){
        this.username = user.getUsername();
        this.nickname = user.getNickname();
    }
}
