package com.example.miniproject.service;

import com.example.miniproject.dto.response.BoardDto;

import java.util.List;

public interface BoardService {
    List<BoardDto> getAllBoards();
}
