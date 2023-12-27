package com.example.member.domain.member.repository;

import com.example.member.domain.member.entity.LoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long> {
}
