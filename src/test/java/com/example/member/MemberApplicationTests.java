package com.example.member;

import com.example.member.domain.member.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@SpringBootTest
class MemberApplicationTests {

	@Autowired
	private MemberRepository memberRepository;

	@Test
	void contextLoads() {

		var data = memberRepository.findAll();
		Assertions.assertThat(data.isEmpty()).isFalse();
	}

	@Test
	void contextLoads2() {

		var data = memberRepository.findAll();
		Assertions.assertThat(data.isEmpty()).isTrue();
	}



}
