package com.example.miniproject.service;

import com.example.miniproject.dto.response.PostDto;
import com.example.miniproject.dto.response.ResponseDto;

import java.util.List;

public interface PostService {
    List<PostDto> getAllPostsByBoardName(String boardName);
    List<PostDto> getPostByTitle(String title);
}
