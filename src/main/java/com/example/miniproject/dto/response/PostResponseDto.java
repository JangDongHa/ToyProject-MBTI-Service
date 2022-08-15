package com.example.miniproject.dto.response;

import com.example.miniproject.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostResponseDto {

    private String title;
    private String username;
    private String content;




    public PostResponseDto(Post savedPost) {
        this.title = savedPost.getTitle();
        this.username = savedPost.getUser().getUsername();
        this.content = savedPost.getContent();


    }


}
