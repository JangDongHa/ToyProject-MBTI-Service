package com.example.miniproject.service.impl;

import com.example.miniproject.domain.board.Board;
import com.example.miniproject.domain.board.BoardRepository;
import com.example.miniproject.domain.comment.Comment;
import com.example.miniproject.domain.comment.CommentRepository;
import com.example.miniproject.domain.post.Post;
import com.example.miniproject.domain.post.PostRepository;
import com.example.miniproject.dto.request.PostRequestDto;
import com.example.miniproject.dto.request.RequestCommentDto;
import com.example.miniproject.dto.response.CommentDto;
import com.example.miniproject.dto.response.PostDto;
import com.example.miniproject.dto.response.PostResponseDto;
import com.example.miniproject.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PostDto> getAllPostsByBoardName(String boardName){
        Board boardPS = boardRepository.findByName(boardName).orElseThrow(IllegalArgumentException::new);
        List<Post> postsPS = postRepository.findAllByBoard(boardPS.getId()).orElseThrow(IllegalArgumentException::new);


        List<PostDto> postDtos = new ArrayList<>();
        postsPS.forEach(post -> postDtos.add(new PostDto(post)));

        return postDtos;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDto> getPostByTitle(String title){
        List<Post> postsPS = postRepository.findAllPostByTitle(title).orElseThrow(IllegalArgumentException::new);
        List<PostDto> postDtos = new ArrayList<>();

        postsPS.forEach(post -> postDtos.add(new PostDto(post)));

        return postDtos;
    }


//    public PostResponseDto createPost(PostRequestDto requestDto) {
//        Post post = new Post(requestDto);
//        Post savedPost = postRepository.save(post);
////        PostResponseDto postDto = new PostResponseDto(savedPost);
////        return postDto;
//        return null;
//    }

    @Transactional
    public CommentDto registerComment(String boardName, Long postId , RequestCommentDto requestCommentDto) {
        Comment comment = new Comment(boardName, postId, requestCommentDto);
        return new CommentDto(commentRepository.save(comment));
    }

    @Transactional
    public CommentDto updateComment(String boardName, Long postId, Long commentId, RequestCommentDto requestCommentDto) {
        Board board = boardRepository.findByName(boardName).orElseThrow();
        Post post = postRepository.findByBoardAndpost_syntax(board.getId(), postId).orElseThrow();
        Comment comment = commentRepository.findByBoardAndpost_syntax(post.getId(), commentId).orElseThrow();

        comment.update(requestCommentDto);
        Comment updatedComment = commentRepository.save(comment);
        return new CommentDto(updatedComment);
    }

//    public PostResponseDto getPost(String boardName, Long postId) {
//        return null;
//    }
}
