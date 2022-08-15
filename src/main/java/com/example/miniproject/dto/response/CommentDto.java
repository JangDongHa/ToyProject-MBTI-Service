package com.example.miniproject.dto.response;

import com.example.miniproject.domain.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentDto {
    private long id;
    private String title;
    private String username;
    private LocalDateTime createAt;

    public CommentDto(Comment comment) {
        setByComment(comment);
    }

    public void setByComment(Comment comment) {
        this.id = comment.getId();
        this.title = comment.getPost().getTitle();
        this.username = comment.getUser().getUsername();
        this.createAt = comment.getCreatedAt();
    }
}