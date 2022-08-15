package com.example.miniproject.dto.response;

import com.example.miniproject.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PostResponseDto {

    private String title;
    private String username;
    private String content;


    public PostResponseDto(HttpStatus ok, PostResponseDto post) {
    }

    public PostResponseDto(Post savedPost) {
    }


}
