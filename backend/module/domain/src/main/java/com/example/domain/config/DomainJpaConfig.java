package com.example.domain.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Domain 모듈의 JPA 자동 구성 설정.
 *
 * Spring Boot AutoConfiguration으로 등록되어,
 * app:web 및 app:batch 실행 시 자동으로 활성화됩니다.
 * 슬라이스 테스트(@WebMvcTest 등)에서는 웹 슬라이스에 불필요한 설정으로 분류되어
 * 자동으로 제외되므로 테스트 충돌이 발생하지 않습니다.
 */
@AutoConfiguration
@EnableJpaRepositories(basePackages = "com.example.domain.repository")
@EntityScan(basePackages = "com.example.domain.entity")
public class DomainJpaConfig {
}
