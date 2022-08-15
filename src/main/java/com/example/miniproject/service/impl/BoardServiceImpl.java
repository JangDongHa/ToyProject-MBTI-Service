package com.example.miniproject.service.impl;

import com.example.miniproject.domain.board.Board;
import com.example.miniproject.domain.board.BoardRepository;
import com.example.miniproject.dto.response.BoardDto;
import com.example.miniproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public List<BoardDto> getAllBoards(){
        List<Board> boardsPS = boardRepository.findAll();
        List<BoardDto> boardDtos = new ArrayList<>();

        boardsPS.forEach(board -> boardDtos.add(new BoardDto(board)));

        return boardDtos;
    }
}
