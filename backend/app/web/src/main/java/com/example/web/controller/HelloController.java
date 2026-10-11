package com.example.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 헬스체크 및 프론트엔드 연동 테스트 API 컨트롤러.
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot!";
    }

    /**
     * React 프론트엔드 연동 테스트용 API (DB 접근 없음)
     * GET /api/hello -> {"message": "Spring Boot API 연결 성공"}
     */
    @GetMapping("/api/hello")
    public Map<String, String> helloApi() {
        return Map.of("message", "Spring Boot API 연결 성공");
    }
}
