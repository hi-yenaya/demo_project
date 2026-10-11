package com.example.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 날짜/시간 관련 공통 유틸리티.
 */
public final class DateTimeUtils {

    private static final DateTimeFormatter DEFAULT_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private DateTimeUtils() {
        // 인스턴스화 방지
    }

    /**
     * LocalDateTime을 기본 형식(yyyy-MM-dd HH:mm:ss) 문자열로 변환한다.
     */
    public static String format(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }
        return dateTime.format(DEFAULT_FORMATTER);
    }

    /**
     * 현재 일시를 기본 형식 문자열로 반환한다.
     */
    public static String nowFormatted() {
        return format(LocalDateTime.now());
    }
}
