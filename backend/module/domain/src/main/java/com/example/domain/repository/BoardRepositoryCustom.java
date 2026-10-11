package com.example.domain.repository;

import com.example.domain.entity.Board;
import java.util.List;

/**
 * Board QueryDSL 커스텀 리포지토리 인터페이스.
 */
public interface BoardRepositoryCustom {

    /**
     * QueryDSL 제목(title) 검색 예시:
     * 제목에 특정 키워드가 포함된 게시글 목록 조회 (seq 역순 정렬)
     */
    List<Board> findByTitleContaining(String title);

    /**
     * QueryDSL 다중 조건 동적 검색 예시:
     * 구분코드(선택) 및 제목 키워드(선택)로 게시글 목록 조회
     */
    List<Board> searchBoards(String boardDivCode, String titleKeyword);
}
