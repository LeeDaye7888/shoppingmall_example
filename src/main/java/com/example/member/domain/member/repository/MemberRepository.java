package com.example.member.domain.member.repository;

import com.example.member.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByIdOrEmail(Long id, String email);

    Optional<Member> findByEmailAndPassword(String email, String password);

    Optional<Member> findByEmail(String email);
}
