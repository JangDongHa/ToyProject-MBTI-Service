package com.example.miniproject.controller;

import com.example.miniproject.dto.request.PostRequestDto;
import com.example.miniproject.dto.request.RequestCommentDto;
import com.example.miniproject.dto.response.CommentDto;
import com.example.miniproject.dto.response.PostDto;
import com.example.miniproject.dto.response.PostResponseDto;
import com.example.miniproject.dto.response.ResponseDto;
import com.example.miniproject.service.impl.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostServiceImpl postService;

    // 게시판 조회
    @GetMapping("/api/board/{boardName}/all")
    public ResponseDto<List<PostDto>> getAllpostsInBoard(@PathVariable String boardName) {
        return new ResponseDto<>(HttpStatus.OK, postService.getAllPostsByBoardName(boardName));
    }

    // 검색 기능 (title 조회)
    @GetMapping("/api/search")
    public ResponseDto<List<PostDto>> searchTitle(String title) {

        return new ResponseDto<>(HttpStatus.OK, postService.getPostByTitle(title));
    }

    @PostMapping("/api/board/{boardName}")
    public ResponseDto<String> createPost(@RequestBody PostRequestDto requestDto) {
        //return new PostResponseDto(HttpStatus.OK, postService.createPost(requestDto));
        return null;

    }
//
//    @GetMapping("/api/board/{boardName}/id/{postId}")
//    public PostResponseDto getPost(@PathVariable String boardName, @PathVariable Long postId) {
//        return postService.getPost(boardName, postId);
//    }

    @PostMapping("/api/{boardName}/{id}/{postId}/comment")
    public ResponseDto<CommentDto> registerComment (String boardName, @PathVariable Long postId, @RequestBody RequestCommentDto requestCommentDto) {
        return new ResponseDto<>(HttpStatus.OK, postService.registerComment(boardName, postId, requestCommentDto));
    }

    @PutMapping("/api/{boardName}/{id}/{postId}/comment/{commentId}")
    public ResponseDto<CommentDto> updateComment (String boardName, @PathVariable Long postId, @PathVariable Long commentId, RequestCommentDto requestCommentDto ){
        return new ResponseDto<>(HttpStatus.OK, postService.updateComment(boardName, postId, commentId,requestCommentDto));
    }

}
