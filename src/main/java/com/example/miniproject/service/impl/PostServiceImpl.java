package com.example.miniproject.service.impl;

import com.example.miniproject.domain.board.Board;
import com.example.miniproject.domain.board.BoardRepository;
import com.example.miniproject.domain.comment.Comment;
import com.example.miniproject.domain.comment.CommentRepository;
import com.example.miniproject.domain.post.Post;
import com.example.miniproject.domain.post.PostRepository;
import com.example.miniproject.domain.user.User;
import com.example.miniproject.domain.user.UserRepository;
import com.example.miniproject.dto.request.PostRequestDto;
import com.example.miniproject.dto.request.RequestCommentDto;
import com.example.miniproject.dto.response.CommentDto;
import com.example.miniproject.dto.response.PostDto;
import com.example.miniproject.dto.response.PostResponseDto;
import com.example.miniproject.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PostDto> getAllPostsByBoardName(String boardName) {
        Board boardPS = boardRepository.findByName(boardName).orElseThrow(IllegalArgumentException::new);
        List<Post> postsPS = postRepository.findAllByBoard(boardPS.getId()).orElseThrow(IllegalArgumentException::new);


        List<PostDto> postDtos = new ArrayList<>();
        postsPS.forEach(post -> postDtos.add(new PostDto(post)));

        return postDtos;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDto> getPostByTitle(String title) {
        List<Post> postsPS = postRepository.findAllPostByTitle(title).orElseThrow(IllegalArgumentException::new);
        List<PostDto> postDtos = new ArrayList<>();

        postsPS.forEach(post -> postDtos.add(new PostDto(post)));

        return postDtos;
    }


    @Transactional
    public CommentDto registerComment(String boardName, String username, RequestCommentDto requestCommentDto) {
        Board board = boardRepository.findByName(boardName).orElseThrow();
        long postSyntax = postRepository.findpost_syntaxByBoardId(board.getId()).orElse(0L);
        Post post = postRepository.findByBoardAndpost_syntax(board.getId(), postSyntax).orElseThrow();
        User user = userRepository.findByUsername(username).orElseThrow();
        Comment comment = Comment.builder().board(board).user(user).post(post).content(requestCommentDto.getContent()).build();
        return new CommentDto(commentRepository.save(comment));
    }

    @Transactional
    public Boolean updateComment(Long commentId, String username, RequestCommentDto requestCommentDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        if(comment.getUser().getUsername().equals(username)){
            comment.update(requestCommentDto);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public Boolean deleteComment(String username, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        if(comment.getUser().getUsername().equals(username)){
            commentRepository.delete(comment);
            return true;
        } else {
            return false;
        }

    }
}


