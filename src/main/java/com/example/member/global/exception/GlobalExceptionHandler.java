package com.example.member.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex, HttpServletRequest request) {
        log.error("request: %s , message: %s".formatted(request.getRequestURI(), ex.getLocalizedMessage()), ex);
        var error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getLocalizedMessage());
        return ResponseEntity.internalServerError().body(error);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleException(BusinessException ex, HttpServletRequest request) {
        log.error("request: %s , message: %s".formatted(request.getRequestURI(), ex.getLocalizedMessage()), ex);
        var error = new ErrorResponse(HttpStatus.valueOf(ex.getErrorCode()).getReasonPhrase(), ex.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.valueOf(ex.getErrorCode())).body(error);
    }
}
