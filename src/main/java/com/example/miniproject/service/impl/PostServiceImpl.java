package com.example.miniproject.service.impl;

import com.example.miniproject.domain.board.Board;
import com.example.miniproject.domain.board.BoardRepository;
import com.example.miniproject.domain.post.Post;
import com.example.miniproject.domain.post.PostRepository;
import com.example.miniproject.dto.request.PostRequestDto;
import com.example.miniproject.dto.request.PostUpdateRequestDto;
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


    public PostResponseDto createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        Post savedPost = postRepository.save(post);

        return new PostResponseDto(savedPost);
    }

    public PostResponseDto getPost(String boardName, Long postId) {
        Board board = boardRepository.findByName(boardName).orElseThrow(IllegalCallerException::new);
        Post post = postRepository.findByNameAndPostSyntax(board, postId);
        PostResponseDto postResponseDto = new PostResponseDto(post);

        postResponseDto.setTitle(post.getTitle());
        postResponseDto.setUsername(post.getUser().getUsername());
        postResponseDto.setContent(post.getContent());

        return postResponseDto;
    }

    public PostResponseDto updatePost(String boardName, Long postId, PostUpdateRequestDto postUpdateRequestDto) {
        Board board = boardRepository.findByName(boardName).orElseThrow(IllegalArgumentException::new);
        Post post = postRepository.findByNameAndPostSyntax(board, postId);
        post.update(postUpdateRequestDto);

        Post updatedPost = postRepository.save(post);
        return new PostResponseDto(updatedPost);

    }
}

