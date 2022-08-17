package com.example.miniproject.dto.response;

import com.example.miniproject.domain.comment.Comment;
import com.example.miniproject.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostResponseDto {

    private String title;
    private String nickname;
    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private List<CommentDto> comments;




    public PostResponseDto(Post savedPost) {
        this.createdAt = savedPost.getCreatedAt();
        this.title = savedPost.getTitle();
        this.nickname = savedPost.getUser().getNickname();
        this.content = savedPost.getContent();
        this.modifiedAt = savedPost.getModifiedAt();
        List<Comment> commentsPS = savedPost.getComments();
        this.comments = new ArrayList<>();
        if (commentsPS != null) // 댓글이 없으면 진행 x
            commentsPS.forEach(comment -> comments.add(new CommentDto(comment)));
    }


}

