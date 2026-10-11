package com.example.web.service;

import com.example.domain.service.BoardService;
import com.example.web.dto.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 웹 계층 전용 비즈니스 서비스.
 * Domain 모듈의 BoardService를 호출하여 웹 DTO로 변환 및 부가 로직을 처리한다.
 */
@Service
@RequiredArgsConstructor
public class WebBoardService {

    private final BoardService boardService;

    public Optional<BoardResponse> getBoard(String boardDivCode, Long seq) {
        return boardService.findBoard(boardDivCode, seq).map(BoardResponse::from);
    }

    public List<BoardResponse> getBoardsByDivCode(String boardDivCode) {
        return boardService.findBoardsByDivCode(boardDivCode).stream()
                .map(BoardResponse::from)
                .toList();
    }

    public List<BoardResponse> searchByTitle(String title) {
        return boardService.searchByTitle(title).stream()
                .map(BoardResponse::from)
                .toList();
    }

    public List<BoardResponse> searchBoards(String boardDivCode, String keyword) {
        return boardService.searchBoards(boardDivCode, keyword).stream()
                .map(BoardResponse::from)
                .toList();
    }
}
