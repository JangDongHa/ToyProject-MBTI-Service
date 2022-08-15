package com.example.miniproject.controller;

import com.example.miniproject.dto.response.BoardDto;
import com.example.miniproject.dto.response.ResponseDto;
import com.example.miniproject.service.impl.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class BoardApiController {
    private final BoardServiceImpl boardService;

    // 전체 게시판 목록 조회
    @GetMapping("/api/board/all")
    public ResponseDto<List<BoardDto>> getAllboards(){
        return new ResponseDto<>(HttpStatus.OK, boardService.getAllBoards());
    }
}
