package com.example.miniproject.domain.comment;

import com.example.miniproject.domain.board.Board;
import com.example.miniproject.domain.post.Post;
import com.example.miniproject.domain.user.User;
import com.example.miniproject.domain.common.Timestamped;
import com.example.miniproject.dto.request.RequestCommentDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@SuperBuilder
@NoArgsConstructor
@Getter
@Entity
public class Comment extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String content;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne
    private User user;

    @JoinColumn(name = "post_id", nullable = false)
    @ManyToOne
    private Post post;

    @JoinColumn(name = "board_id", nullable = false)
    @ManyToOne
    private Board board;

    public Comment(RequestCommentDto requestCommentDto) {
        this.content = requestCommentDto.getContent();
    }

    public void update(RequestCommentDto requestCommentDto) {
        this.content = requestCommentDto.getContent();
    }
}
