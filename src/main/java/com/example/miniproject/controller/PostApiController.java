package com.example.miniproject.controller;

import com.example.miniproject.config.jwt.token.RequestToken;
import com.example.miniproject.dto.request.PostRequestDto;
import com.example.miniproject.dto.request.RequestCommentDto;
import com.example.miniproject.dto.request.PostUpdateRequestDto;
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

    //게시글 등록
    @PostMapping("/api/board/{boardName}")
    public ResponseDto<Boolean> createPost(@PathVariable String boardName,
                                                   @RequestBody PostRequestDto requestDto,
                                                   HttpServletRequest request){
        postService.createPost(boardName, getUsername(request), requestDto);
        return new ResponseDto<>(HttpStatus.OK, true);
    }


    //게시글 조회
    @GetMapping("/api/board/{boardName}/id/{postSyntax}")

    public ResponseDto<PostResponseDto> getPost(@PathVariable String boardName,
                                                @PathVariable Long postSyntax){
        return new ResponseDto<>(HttpStatus.OK, postService.getPost(boardName,postSyntax));
    }
    
    //게시글 수정
    @PutMapping("/api/board/{boardName}/id/{postSyntax}")
    public ResponseDto<PostResponseDto> updatePost(@PathVariable String boardName,
                                                   @PathVariable Long postSyntax,
                                                   @RequestBody PostUpdateRequestDto postUpdateRequestDto,
                                                   HttpServletRequest request){

        return new ResponseDto<>(HttpStatus.OK,postService.updatePost(boardName, postSyntax, postUpdateRequestDto, getUsername(request)) );
    }

    //게시글 삭제
    @DeleteMapping("/api/board/{boardName}/id/{postSyntax}")
    public ResponseDto<Boolean> deletePost(@PathVariable String boardName,
                                           @PathVariable Long postSyntax,
                                           HttpServletRequest request){
        postService.deletePost(boardName, postSyntax, getUsername(request));
        return new ResponseDto<>(HttpStatus.OK, true);
    }


    @PostMapping("/api/board/{boardName}/id/{postSyntax}/comment")
    public ResponseDto<Boolean> registerComment (@PathVariable String boardName, @PathVariable long postSyntax, HttpServletRequest request, @RequestBody RequestCommentDto requestCommentDto) {
        postService.registerComment(boardName,getUsername(request),requestCommentDto, postSyntax);
        return new ResponseDto<>(HttpStatus.OK, true);
    }



    @PutMapping("/api/board/{boardName}/id/{postSyntax}/comment/{commentId}")
    public ResponseDto<Boolean> updateComment (@PathVariable Long commentId,HttpServletRequest request,@RequestBody RequestCommentDto requestCommentDto ) {
        postService.updateComment(commentId, getUsername(request), requestCommentDto);
        return new ResponseDto<>(HttpStatus.OK, true);
    }


    @DeleteMapping("/api/board/{boardName}/id/{postId}/comment/{commentId}")
    public  ResponseDto<Boolean> deleteComment (HttpServletRequest request, @PathVariable Long commentId){
        postService.deleteComment(getUsername(request), commentId);
        return new ResponseDto<>(HttpStatus.OK, true);
    }
    
    


}

