package com.example.miniproject.controller;

import com.example.miniproject.dto.request.PostRequestDto;
import com.example.miniproject.dto.request.PostUpdateRequestDto;
import com.example.miniproject.dto.response.PostDto;
import com.example.miniproject.dto.response.PostResponseDto;
import com.example.miniproject.dto.response.ResponseDto;
import com.example.miniproject.service.impl.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostServiceImpl postService;

    // 게시판 조회
    @GetMapping("/api/board/{boardName}/all")
    public ResponseDto<List<PostDto>> getAllpostsInBoard(@PathVariable String boardName){
        return new ResponseDto<>(HttpStatus.OK, postService.getAllPostsByBoardName(boardName));
    }

    // 검색 기능 (title 조회)
    @GetMapping("/api/search")
    public ResponseDto<List<PostDto>> searchTitle(String title){

        return new ResponseDto<>(HttpStatus.OK, postService.getPostByTitle(title));
    }

    //게시글 등록
    @PostMapping("/api/board/{boardName}")
    public ResponseDto<PostResponseDto> createPost(@RequestBody PostRequestDto requestDto){
        return new ResponseDto<>(HttpStatus.OK, postService.createPost(requestDto));


    }

    //게시글 조회
    @GetMapping("/api/board/{boardName}/id/{postId}")
    public ResponseDto<PostResponseDto> getPost(String boardName, @PathVariable Long postId){
        return new ResponseDto<>(HttpStatus.OK, postService.getPost(boardName,postId));

    }

    //게시글 수정
    @Transactional
    @PutMapping("/api/board/{boardName}/id/{postId}")
    public ResponseDto<PostResponseDto> updatePost(String boardName,
                                                   @PathVariable Long postId,
                                                   @RequestBody PostUpdateRequestDto postUpdateRequestDto){
        return new ResponseDto<>(HttpStatus.OK, postService.updatePost(boardName,postId, postUpdateRequestDto));
    }

    //게시글 삭제
    @DeleteMapping("/api/board/{boardName}/id/{postId}")
    public String deletePost(String boardName,
                             @PathVariable Long postId){
        postService.deletePost(boardName, postId);
        return "삭제되었습니다.";
    }
}
