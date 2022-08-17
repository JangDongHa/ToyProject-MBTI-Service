package com.example.miniproject.dto.response;

import com.example.miniproject.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostResponseDto {

    private String title;
    private String username;
    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;




    public PostResponseDto(Post savedPost) {
        this.createdAt = savedPost.getCreatedAt();
        this.title = savedPost.getTitle();
        this.username = savedPost.getUser().getUsername();
        this.content = savedPost.getContent();
        this.modifiedAt = savedPost.getModifiedAt();
    }


}

