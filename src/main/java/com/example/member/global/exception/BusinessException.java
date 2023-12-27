package com.example.member.global.exception;

import lombok.Getter;

public class BusinessException extends RuntimeException {
    private final String errorCode;
    public BusinessException(String errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }

    public BusinessException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        // todo: 에러 코드 정의 필요
        return Integer.parseInt(this.errorCode);
    }
}
