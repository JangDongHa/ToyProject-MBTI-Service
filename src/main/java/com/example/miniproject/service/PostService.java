package com.example.miniproject.service;

import com.example.miniproject.dto.request.PostRequestDto;
import com.example.miniproject.dto.request.PostUpdateRequestDto;
import com.example.miniproject.dto.request.RequestCommentDto;
import com.example.miniproject.dto.response.CommentDto;
import com.example.miniproject.dto.response.PostDto;
import com.example.miniproject.dto.response.PostResponseDto;
import com.example.miniproject.dto.response.ResponseDto;

import java.util.List;

public interface PostService {
    List<PostDto> getAllPostsByBoardName(String boardName);
    List<PostDto> getPostByTitle(String title);
    CommentDto registerComment(String boardName, String username, RequestCommentDto requestCommentDto, long reqPostSyntax);
    Boolean updateComment(Long commentId, String username, RequestCommentDto requestCommentDto);
    Boolean deleteComment(String username, Long commentId);
    PostResponseDto createPost(String boardName, String username, PostRequestDto requestDto);
    PostResponseDto getPost(String boardName, Long postSyntax);
    PostResponseDto updatePost(String boardName,
                               Long postSyntax,
                               PostUpdateRequestDto postUpdateRequestDto,
                               String username);
    void deletePost(String boardName,
                    Long postSyntax,
                    String username);
}
