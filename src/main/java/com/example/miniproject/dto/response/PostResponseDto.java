package com.example.miniproject.dto.response;

import com.example.miniproject.domain.post.Post;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PostResponseDto {

    private String title;
    private String username;
    private String content;


    public PostResponseDto(HttpStatus ok, PostResponseDto post) {
    }
}
