package com.example.web.controller;

import com.example.web.dto.BoardResponse;
import com.example.web.service.WebBoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BoardController.class)
class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WebBoardService webBoardService;

    @MockBean
    private JpaMetamodelMappingContext jpaMetamodelMappingContext;

    @Test
    @DisplayName("게시글 단건 조회 성공 테스트")
    void getBoard_success() throws Exception {
        // given
        BoardResponse mockResponse = BoardResponse.builder()
                .boardDivCode("NOTICE")
                .seq(1L)
                .boardTitle("공지사항 제목")
                .boardCn("공지사항 내용입니다.")
                .build();

        given(webBoardService.getBoard("NOTICE", 1L)).willReturn(Optional.of(mockResponse));

        // when & then
        mockMvc.perform(get("/api/boards/NOTICE/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.boardDivCode").value("NOTICE"))
                .andExpect(jsonPath("$.seq").value(1L))
                .andExpect(jsonPath("$.boardTitle").value("공지사항 제목"))
                .andExpect(jsonPath("$.boardCn").value("공지사항 내용입니다."));
    }

    @Test
    @DisplayName("QueryDSL 제목 검색 API 테스트")
    void searchByTitle_success() throws Exception {
        // given
        BoardResponse mockResponse = BoardResponse.builder()
                .boardDivCode("NOTICE")
                .seq(1L)
                .boardTitle("테스트 공지사항")
                .boardCn("내용")
                .build();

        given(webBoardService.searchByTitle("공지")).willReturn(List.of(mockResponse));

        // when & then
        mockMvc.perform(get("/api/boards/search")
                        .param("title", "공지"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].boardDivCode").value("NOTICE"))
                .andExpect(jsonPath("$[0].seq").value(1L))
                .andExpect(jsonPath("$[0].boardTitle").value("테스트 공지사항"));
    }

    @Test
    @DisplayName("QueryDSL 다중 조건 검색 API 테스트")
    void searchBoards_success() throws Exception {
        // given
        BoardResponse mockResponse = BoardResponse.builder()
                .boardDivCode("NOTICE")
                .seq(1L)
                .boardTitle("공지사항 제목")
                .boardCn("공지사항 내용")
                .build();

        given(webBoardService.searchBoards("NOTICE", "공지")).willReturn(List.of(mockResponse));

        // when & then
        mockMvc.perform(get("/api/boards")
                        .param("boardDivCode", "NOTICE")
                        .param("keyword", "공지"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].boardDivCode").value("NOTICE"))
                .andExpect(jsonPath("$[0].boardTitle").value("공지사항 제목"));
    }
}
