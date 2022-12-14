package com.example.miniproject.domain.comment;

import com.example.miniproject.domain.post.Post;
import com.example.miniproject.domain.user.User;
import com.example.miniproject.domain.common.Timestamped;
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

}
