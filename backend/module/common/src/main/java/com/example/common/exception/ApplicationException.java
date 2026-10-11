package com.example.common.exception;

/**
 * 애플리케이션 공통 기반 예외 클래스.
 * 모든 비즈니스 예외는 이 클래스를 상속하여 작성한다.
 */
public class ApplicationException extends RuntimeException {

    private final String errorCode;

    public ApplicationException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ApplicationException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
