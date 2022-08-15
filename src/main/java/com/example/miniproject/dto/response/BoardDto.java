package com.example.miniproject.dto.response;

import com.example.miniproject.domain.board.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BoardDto {
    private String boardName;
    private String imageName;
    private String boardDescription;

    public BoardDto(Board board){
        toBoard(board);
    }

    public void toBoard(Board board){
        this.boardName = board.getName();
        this.imageName = board.getImageName();
        this.boardDescription = board.getDescription();
    }
}
