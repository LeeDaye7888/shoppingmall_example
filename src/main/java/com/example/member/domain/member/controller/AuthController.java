package com.example.member.domain.member.controller;

import com.example.member.domain.member.dto.LoginRequest;
import com.example.member.domain.member.dto.MemberResponse;
import com.example.member.domain.member.dto.SignUpRequest;
import com.example.member.domain.member.service.MemberService;
import com.example.member.global.config.security.model.TokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
@RestController
public class AuthController {
    private final MemberService memberService;

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/signup")
    public MemberResponse 회원_가입(@RequestBody SignUpRequest request) {
        try {
            var newMember = memberService.회원_가입(request);
            return new MemberResponse(newMember.getId(), newMember.getEmail(), newMember.getUsername());
        } catch (Exception e) {
            throw e;
        }

    }

    @ResponseStatus(code = HttpStatus.OK)
    @PostMapping("/login")
    public TokenInfo 로그인(@RequestBody LoginRequest request) {
        try {
            var token = memberService.로그인(request);
            return token;
        } catch (Exception e) {
            throw e;
        }
    }
}
