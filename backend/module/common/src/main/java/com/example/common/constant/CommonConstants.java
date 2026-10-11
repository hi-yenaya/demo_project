package com.example.common.constant;

/**
 * 애플리케이션 공통 상수.
 */
public final class CommonConstants {

    private CommonConstants() {
        // 인스턴스화 방지
    }

    /** API 응답 상태 코드 */
    public static final String SUCCESS = "SUCCESS";
    public static final String FAILURE = "FAILURE";

    /** 기본 날짜 형식 */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
}
