package com.example.miniproject.domain.board;

import com.example.miniproject.domain.post.Post;
import com.example.miniproject.domain.user.User;
import com.example.miniproject.domain.common.Timestamped;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@SuperBuilder
@Getter
@Entity
@NoArgsConstructor
public class Board extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(nullable = false, length = 50)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column
    private String imageName;

    @Column
    private String description;



}
