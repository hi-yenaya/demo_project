package com.example.batch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Batch 애플리케이션의 JPA Auditing 활성화 설정.
 */
@Configuration
@EnableJpaAuditing
public class BatchJpaAuditingConfig {
}
