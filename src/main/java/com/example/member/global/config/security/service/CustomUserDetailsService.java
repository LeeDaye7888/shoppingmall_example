package com.example.member.global.config.security.service;

import com.example.member.domain.member.entity.Member;
import com.example.member.domain.member.repository.MemberRepository;
import com.example.member.global.config.security.model.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var member = memberRepository.findByEmail(username).orElseThrow();
        return createUserDetails(member);
    }

    private UserDetails createUserDetails(Member member) {
        var roles = new ArrayList<SimpleGrantedAuthority>();
        roles.add(new SimpleGrantedAuthority(member.getAuthority().name()));
        var list = roles.stream()
                .map(authority -> (GrantedAuthority) authority)
                .collect(Collectors.toList());
        return new SecurityUser(member.getId(), member.getPassword(), passwordEncoder.encode(member.getPassword()), list);
    }
}
