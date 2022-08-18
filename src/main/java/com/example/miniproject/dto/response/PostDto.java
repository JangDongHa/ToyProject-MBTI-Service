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
public class PostDto {
    private long id;
    private String boardName;
    private String title;
    private String nickname;
    private LocalDateTime createAt;

    public PostDto(Post post){
        setByPost(post);
    }

    public void setByPost(Post post){
        this.id = post.getPost_syntax();
        this.boardName = post.getBoard().getName();
        this.title = post.getTitle();
        this.nickname = post.getUser().getNickname();
        this.createAt = post.getCreatedAt();
    }
}
