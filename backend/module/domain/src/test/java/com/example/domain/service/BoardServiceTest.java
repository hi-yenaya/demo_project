package com.example.domain.service;

import com.example.domain.entity.Board;
import com.example.domain.entity.BoardId;
import com.example.domain.repository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    @Mock
    private BoardRepository boardRepository;

    @InjectMocks
    private BoardService boardService;

    @Test
    @DisplayName("복합키로 게시글 조회")
    void findBoard() {
        // given
        Board board = Board.builder()
                .boardDivCode("FAQ")
                .seq(1L)
                .boardTitle("자주 묻는 질문")
                .boardCn("답변 내용")
                .build();

        given(boardRepository.findById(new BoardId("FAQ", 1L)))
                .willReturn(Optional.of(board));

        // when
        Optional<Board> result = boardService.findBoard("FAQ", 1L);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getBoardTitle()).isEqualTo("자주 묻는 질문");
    }

    @Test
    @DisplayName("QueryDSL 제목 키워드 검색 호출 검증")
    void searchByTitle() {
        // given
        Board board = Board.builder()
                .boardDivCode("NOTICE")
                .seq(1L)
                .boardTitle("시스템 공지")
                .boardCn("내용")
                .build();

        given(boardRepository.findByTitleContaining("공지"))
                .willReturn(List.of(board));

        // when
        List<Board> result = boardService.searchByTitle("공지");

        // then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getBoardTitle()).isEqualTo("시스템 공지");
    }

    @Test
    @DisplayName("QueryDSL 조건 검색 호출 검증")
    void searchBoards() {
        // given
        Board board = Board.builder()
                .boardDivCode("NOTICE")
                .seq(2L)
                .boardTitle("시스템 점검")
                .boardCn("점검 안내")
                .build();

        given(boardRepository.searchBoards("NOTICE", "점검"))
                .willReturn(List.of(board));

        // when
        List<Board> result = boardService.searchBoards("NOTICE", "점검");

        // then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getBoardTitle()).isEqualTo("시스템 점검");
    }
}
