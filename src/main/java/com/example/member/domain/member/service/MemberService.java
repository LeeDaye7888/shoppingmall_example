package com.example.member.domain.member.service;

import com.example.member.domain.member.dto.LoginRequest;
import com.example.member.domain.member.dto.MemberRequest;
import com.example.member.domain.member.dto.MemberResponse;
import com.example.member.domain.member.dto.SignUpRequest;
import com.example.member.domain.member.entity.Authority;
import com.example.member.domain.member.entity.LoginHistory;
import com.example.member.domain.member.entity.Member;
import com.example.member.domain.member.repository.LoginHistoryRepository;
import com.example.member.domain.member.repository.MemberRepository;
import com.example.member.global.config.security.JwtTokenProvider;
import com.example.member.global.config.security.model.TokenInfo;
import com.example.member.global.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final LoginHistoryRepository loginHistoryRepository;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    public Member 회원_가입(SignUpRequest request) {
        var member = new Member(null, request.password(), request.email(), request.username(), null, null, Authority.USER);
        var newMember = memberRepository.save(member);
        return newMember;
    }

    public TokenInfo 로그인(LoginRequest request) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        var authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        try {
            var member = this.memberRepository.findByEmailAndPassword(request.email(), request.password())
                    .orElseThrow(() -> new BusinessException("404", "not found member"));

            // todo: 로그인 히스토리가 입력이 안되어서 로그인 오류가 생기면 안된다
            // todo: AOP 기능으로 나중에 분리해야 함
            var history = new LoginHistory(null, member, null);
            loginHistoryRepository.save(history);
        } catch (Exception e) {
            log.warn("message: %s".formatted(e.getLocalizedMessage()), e);
        }

        return jwtTokenProvider.createToken(authentication);
    }

    public MemberResponse 회원_정보_조회(Long memberId, String email) {
        var member = memberRepository.findByIdOrEmail(memberId, email);
        return member.map(x -> new MemberResponse(x.getId(), x.getEmail(), x.getUsername()))
                .orElseThrow(() -> new BusinessException("404", "not found member"));

    }

    public List<MemberResponse> 회원_정보_조회_페이징(Pageable pageable) {
        var members = memberRepository.findAll(pageable);
        return members.map(x -> new MemberResponse(x.getId(), x.getEmail(), x.getUsername()))
                .toList();
    }

    public void 회원_정보_수정(long memberId, MemberRequest request) {
        var member = memberRepository.findById(memberId);
        if(member.isPresent()) {
            // todo: 수정할 데이터를 넣어준다
            var updatedMember = member.get().updateMember(request);
            memberRepository.save(updatedMember);
        } else {
            throw new BusinessException("404", "not found member");
        }
    }

}
