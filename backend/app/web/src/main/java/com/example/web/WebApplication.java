package com.example.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Web 애플리케이션 진입점.
 *
 * scanBasePackages: com.example (web, domain, common 전체 컴포넌트 스캔)
 * JPA 및 QueryDSL 설정은 module:domain의 AutoConfiguration을 통해 자동 활성화됩니다.
 */
@SpringBootApplication(scanBasePackages = "com.example")
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
