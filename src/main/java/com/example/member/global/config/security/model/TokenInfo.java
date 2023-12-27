package com.example.member.global.config.security.model;

public record TokenInfo(
        String grantType, String accessToken
) {
}
