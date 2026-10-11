package com.example.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Batch 애플리케이션 진입점.
 *
 * - spring.main.web-application-type=none 설정으로 내장 웹 서버 없이 실행
 * - @EnableScheduling: @Scheduled 기반 주기적 데이터 수집 및 통계 배치 활성화
 * - scanBasePackages: com.example (batch, domain, common 전체 컴포넌트 스캔)
 * - JPA 및 QueryDSL 설정은 module:domain의 AutoConfiguration을 통해 자동 활성화
 *
 * [추후 Spring Batch 도입 시]
 * @EnableBatchProcessing 어노테이션 추가 및 build.gradle 의존성 주석 해제
 */
@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.example")
public class BatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchApplication.class, args);
    }
}
