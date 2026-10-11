package com.example.domain.service;

import com.example.domain.entity.Board;
import com.example.domain.entity.BoardId;
import com.example.domain.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Board 도메인 비즈니스 서비스 (조회 중심).
 * Web 및 Batch 모듈에서 재사용 가능한 공통 도메인 서비스.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    /**
     * 복합키(boardDivCode, seq)를 통한 단건 조회
     */
    public Optional<Board> findBoard(String boardDivCode, Long seq) {
        return boardRepository.findById(new BoardId(boardDivCode, seq));
    }

    /**
     * 게시판 구분코드 기준 목록 조회
     */
    public List<Board> findBoardsByDivCode(String boardDivCode) {
        return boardRepository.findByBoardDivCodeOrderBySeqDesc(boardDivCode);
    }

    /**
     * QueryDSL 기반 제목(title) 키워드 검색
     */
    public List<Board> searchByTitle(String title) {
        return boardRepository.findByTitleContaining(title);
    }

    /**
     * QueryDSL 기반 조건 검색 (구분코드, 제목 키워드)
     */
    public List<Board> searchBoards(String boardDivCode, String titleKeyword) {
        return boardRepository.searchBoards(boardDivCode, titleKeyword);
    }
}
