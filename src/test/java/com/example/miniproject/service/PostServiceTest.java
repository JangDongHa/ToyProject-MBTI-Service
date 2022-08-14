package com.example.miniproject.service;

import com.example.miniproject.domain.board.Board;
import com.example.miniproject.domain.board.BoardRepository;
import com.example.miniproject.domain.post.Post;
import com.example.miniproject.domain.post.PostRepository;
import com.example.miniproject.domain.user.User;
import com.example.miniproject.domain.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PostServiceTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository;

    @BeforeEach
    public void settingData(){
        Board board = Board.builder().id(1L).description("test").name("testName").imageName("test").build();
        User user = User.builder().id(1L).username("test").password("test").build();

        boardRepository.save(board);
        userRepository.save(user);
        Post post = Post.builder()
                .id(1L)
                .title("test title")
                .content("test")
                .user(user)
                .board(board)
                .build();
        Post post2 = Post.builder()
                .id(2L)
                .title("test title2")
                .content("test2")
                .user(user)
                .board(board)
                .build();

        postRepository.save(post);
        postRepository.save(post2);
    }

    @Test
    @Transactional
    public void getpostsByBoardName(){
        // Given
        Board board = Board.builder().id(1L).description("test").name("testName").imageName("test").build();
        // When
        List<Post> posts = postRepository.findAllByBoard(board).get();
        // Then
        assertEquals(2, posts.size());
    }
}
