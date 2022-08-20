package com.example.miniproject.domain.post;

import com.example.miniproject.domain.user.User;
import com.example.miniproject.domain.board.Board;
import com.example.miniproject.domain.comment.Comment;
import com.example.miniproject.domain.common.Timestamped;
import com.example.miniproject.dto.request.PostRequestDto;
import com.example.miniproject.dto.request.PostUpdateRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Post extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long post_syntax;

    @Column(length = 100)
    private String title;

    @Lob
    private String content;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne
    private User user;


    @JoinColumn(name = "board_id", nullable = false)
    @ManyToOne
    private Board board;



    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"post"})
    @OrderBy("id desc")
    private List<Comment> comments;

    public void update(PostUpdateRequestDto postUpdateRequestDto) {
        this.title = postUpdateRequestDto.getTitle();
        this.content = postUpdateRequestDto.getContent();
    }


}
