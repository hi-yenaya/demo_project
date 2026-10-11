package com.example.web.dto;

import com.example.domain.entity.Board;
import lombok.Builder;
import lombok.Getter;

/**
 * 게시판 웹 응답 DTO.
 */
@Getter
@Builder
public class BoardResponse {

    private String boardDivCode;
    private Long seq;
    private String boardTitle;
    private String boardCn;

    public static BoardResponse from(Board board) {
        return BoardResponse.builder()
                .boardDivCode(board.getBoardDivCode())
                .seq(board.getSeq())
                .boardTitle(board.getBoardTitle())
                .boardCn(board.getBoardCn())
                .build();
    }
}
