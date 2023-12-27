package com.example.member.controller;

import com.example.member.domain.member.controller.AuthController;
import com.example.member.domain.member.controller.MemberController;
import com.example.member.domain.member.dto.SignUpRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class MemberControllerTest {

    @Autowired
    private AuthController authController;


    @Test
    public void test() {
        var member = new SignUpRequest("1234@test.com", "1234", "tester");
        var newMember = this.authController.회원_가입(member);
        Assertions.assertThat(member.email()).isEqualTo(newMember.email());
    }
}
