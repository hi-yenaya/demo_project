package com.example.domain.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Board 엔티티의 복합 기본키 (board_div_code, seq).
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BoardId implements Serializable {

    private String boardDivCode;
    private Long seq;
}
