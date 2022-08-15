package com.example.miniproject.service.impl;

import com.example.miniproject.domain.board.Board;
import com.example.miniproject.domain.board.BoardRepository;
import com.example.miniproject.domain.post.Post;
import com.example.miniproject.domain.post.PostRepository;
import com.example.miniproject.dto.request.PostRequestDto;
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

    @Transactional(readOnly = true)
    public List<PostDto> getAllPostsByBoardName(String boardName){
        Board boardPS = boardRepository.findByName(boardName).orElseThrow(IllegalArgumentException::new);
        List<Post> postsPS = postRepository.findAllByBoard(boardPS).orElseThrow(IllegalArgumentException::new);

        List<PostDto> postDtos = new ArrayList<>();
        postsPS.forEach(post -> postDtos.add(new PostDto(post)));

        return postDtos;
    }

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
        PostResponseDto postDto = new PostResponseDto(savedPost);
        return postDto;
    }


    public PostResponseDto getPost(Long postId) {
    }
}
