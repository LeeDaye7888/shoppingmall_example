package com.example.member.service;

import com.example.member.domain.member.dto.LoginRequest;
import com.example.member.domain.member.entity.Authority;
import com.example.member.domain.member.entity.Member;
import com.example.member.domain.member.repository.MemberRepository;
import com.example.member.domain.member.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("로그인 테스트")
    void loginTest() {
        var member = new Member(null, "1234", "1234@test.com", "tester", null, null, Authority.USER);
        var newMember = memberRepository.save(member);

        var request = new LoginRequest(newMember.getEmail(), newMember.getPassword());
        var token = memberService.로그인(request);
        Assertions.assertThat(token.grantType()).isEqualTo("Bearer");

    }
}
