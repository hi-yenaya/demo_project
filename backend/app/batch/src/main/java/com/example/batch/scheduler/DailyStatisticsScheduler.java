package com.example.batch.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 일일 통계 스케줄러.
 * 매일 새벽 2시에 전일 데이터 통계 및 가공 작업을 실행한다.
 *
 * [Spring Batch 도입 시]
 * @Scheduled 메서드 대신 JobLauncher를 이용해 청크 기반 배치 Job을 실행하도록 변경한다.
 */
@Slf4j
@Component
public class DailyStatisticsScheduler {

    /**
     * 매일 02:00에 일일 통계 작업 실행.
     * cron 표현식: 초 분 시 일 월 요일
     */
    @Scheduled(cron = "0 0 2 * * *")
    public void processDailyStatistics() {
        log.info("[DailyStatisticsScheduler] 일일 통계 처리 시작: {}", java.time.LocalDateTime.now());
        // TODO: 통계 처리 서비스 호출
        // dailyStatisticsService.process();
        log.info("[DailyStatisticsScheduler] 일일 통계 처리 완료");
    }
}
