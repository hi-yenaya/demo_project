package com.example.batch.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 데이터 수집 스케줄러.
 * 주기적으로 외부 데이터를 수집하거나 내부 데이터를 갱신하는 작업을 실행한다.
 *
 * [Spring Batch 도입 시]
 * @Scheduled 메서드 대신 JobLauncher를 이용해 Job을 실행하도록 변경한다.
 */
@Slf4j
@Component
public class DataCollectionScheduler {

    /**
     * 매 10분마다 데이터 수집 작업 실행.
     * cron 표현식: 초 분 시 일 월 요일
     */
    @Scheduled(cron = "0 */10 * * * *")
    public void collectData() {
        log.info("[DataCollectionScheduler] 데이터 수집 작업 시작: {}", java.time.LocalDateTime.now());
        // TODO: 데이터 수집 서비스 호출
        // dataCollectionService.collect();
        log.info("[DataCollectionScheduler] 데이터 수집 작업 완료");
    }
}
