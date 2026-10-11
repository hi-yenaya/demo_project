package com.example.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Web 애플리케이션의 JPA Auditing 활성화 설정.
 *
 * @EnableJpaAuditing을 실행 애플리케이션에서 선언함으로써
 * @WebMvcTest 슬라이스 테스트에서 JPA metamodel 오류가 발생하는 것을 방지한다.
 */
@Configuration
@EnableJpaAuditing
public class WebJpaAuditingConfig {
}
