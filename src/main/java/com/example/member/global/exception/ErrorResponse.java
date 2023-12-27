package com.example.member.global.exception;

public record ErrorResponse(
        String code, String message
) {
}
