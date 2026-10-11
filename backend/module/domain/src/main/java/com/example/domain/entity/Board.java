package com.example.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 게시판 Entity (board).
 * 복합키: board_div_code, seq
 */
@Entity
@Table(name = "board")
@IdClass(BoardId.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Board {

    @Id
    @Column(name = "board_div_code", length = 50, nullable = false)
    private String boardDivCode;

    @Id
    @Column(name = "seq", nullable = false)
    private Long seq;

    @Column(name = "board_title", length = 200)
    private String boardTitle;

    @Column(name = "board_cn", columnDefinition = "TEXT")
    private String boardCn;
}
