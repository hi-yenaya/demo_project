package com.example.domain.repository;

import com.example.domain.entity.Board;
import com.example.domain.entity.BoardId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Board 기본 JPA 리포지토리.
 * JpaRepository의 기본 CRUD 및 QueryDSL 커스텀 메서드를 상속받아 제공한다.
 */
@Repository
public interface BoardRepository extends JpaRepository<Board, BoardId>, BoardRepositoryCustom {

    /**
     * 구분코드별 게시글 목록 조회 (seq 역순 정렬)
     */
    List<Board> findByBoardDivCodeOrderBySeqDesc(String boardDivCode);
}
