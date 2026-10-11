package com.example.web.controller;

import com.example.web.dto.BoardResponse;
import com.example.web.service.WebBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 게시판 웹 REST 컨트롤러.
 */
@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final WebBoardService webBoardService;

    /**
     * 게시글 단건 조회 (복합키: boardDivCode, seq)
     * 예: GET /api/boards/NOTICE/1
     */
    @GetMapping("/{boardDivCode}/{seq}")
    public ResponseEntity<BoardResponse> getBoard(
            @PathVariable String boardDivCode,
            @PathVariable Long seq) {
        return webBoardService.getBoard(boardDivCode, seq)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * QueryDSL 제목(title) 키워드 검색 API
     * 예: GET /api/boards/search?title=공지
     */
    @GetMapping("/search")
    public ResponseEntity<List<BoardResponse>> searchByTitle(
            @RequestParam String title) {
        return ResponseEntity.ok(webBoardService.searchByTitle(title));
    }

    /**
     * 게시글 다중 조건 검색 (QueryDSL 예시)
     * 예: GET /api/boards?boardDivCode=NOTICE&keyword=안내
     */
    @GetMapping
    public ResponseEntity<List<BoardResponse>> searchBoards(
            @RequestParam(required = false) String boardDivCode,
            @RequestParam(required = false) String keyword) {
        return ResponseEntity.ok(webBoardService.searchBoards(boardDivCode, keyword));
    }
}
