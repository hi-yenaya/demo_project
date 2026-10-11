package com.example.domain.repository;

import com.example.domain.entity.Board;
import com.example.domain.entity.QBoard;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.example.domain.entity.QBoard.board;

/**
 * Board QueryDSL 커스텀 리포지토리 구현체.
 */
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Board> findByTitleContaining(String title) {
        return queryFactory
                .selectFrom(board)
                .where(containsTitle(title))
                .orderBy(board.seq.desc())
                .fetch();
    }

    @Override
    public List<Board> searchBoards(String boardDivCode, String titleKeyword) {
        return queryFactory
                .selectFrom(board)
                .where(
                        eqBoardDivCode(boardDivCode),
                        containsTitle(titleKeyword)
                )
                .orderBy(board.seq.desc())
                .fetch();
    }

    private BooleanExpression eqBoardDivCode(String boardDivCode) {
        return StringUtils.hasText(boardDivCode) ? board.boardDivCode.eq(boardDivCode) : null;
    }

    private BooleanExpression containsTitle(String titleKeyword) {
        return StringUtils.hasText(titleKeyword) ? board.boardTitle.contains(titleKeyword) : null;
    }
}
