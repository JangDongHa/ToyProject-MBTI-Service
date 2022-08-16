package com.example.miniproject.controller;

import com.example.miniproject.config.jwt.token.RequestToken;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostServiceImpl postService;

    private String getUsername(HttpServletRequest request){
        RequestToken requestToken = new RequestToken(request);
        return requestToken.getUsername().orElseThrow();
    }

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

    @PostMapping("/api/{boardName}/id/{postSyntax}/comment")
    public ResponseDto<Boolean> registerComment (@PathVariable String boardName, HttpServletRequest request, @RequestBody RequestCommentDto requestCommentDto) {
        postService.registerComment(boardName,getUsername(request),requestCommentDto);
        return new ResponseDto<>(HttpStatus.OK, true);
    }



    @PutMapping("/api/{boardName}/id/{postSyntax}/comment/{commentId}")
    public ResponseDto<Boolean> updateComment (@PathVariable Long commentId,HttpServletRequest request,@RequestBody RequestCommentDto requestCommentDto ){
        postService.updateComment(commentId,getUsername(request),requestCommentDto);
        return new ResponseDto<>(HttpStatus.OK, true);
    }

    @DeleteMapping("/api/{boardName}/id/{postId}/comment/{commentId}")
    public  ResponseDto<Boolean> deleteComment (HttpServletRequest request, @PathVariable Long commentId){
        postService.deleteComment(getUsername(request), commentId);
        return new ResponseDto<>(HttpStatus.OK, true);
    }


}

