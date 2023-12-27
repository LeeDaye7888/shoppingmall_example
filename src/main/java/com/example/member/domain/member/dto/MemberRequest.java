package com.example.member.domain.member.dto;

public record MemberRequest(
        String username,
        String password
) {
}
