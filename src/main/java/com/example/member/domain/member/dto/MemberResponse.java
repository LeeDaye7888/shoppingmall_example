package com.example.member.domain.member.dto;

public record MemberResponse(
        Long id,
        String email,
        String username
) {
}
