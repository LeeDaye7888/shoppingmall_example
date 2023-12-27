package com.example.member.global.config.security;

import com.example.member.domain.member.entity.Authority;
import com.example.member.global.config.security.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 서버에 인증정보를 저장하지 않기에 csrf를 사용하지 않는다.
        http.csrf().disable();
        // form 기반의 로그인에 대해 비 활성화하며 커스텀으로 구성한 필터를 사용한다.
        http.formLogin().disable();
        // 토큰을 활용하는 경우 모든 요청에 대해 '인가'에 대해서 사용.
        http.authorizeHttpRequests((auth) ->
                auth.mvcMatchers("/auth/signup").permitAll()
                        .mvcMatchers("/auth/login").permitAll()
                        .mvcMatchers("/swagger-ui/**").permitAll()
                        .mvcMatchers("/v3/api-docs/**").permitAll()
                        .mvcMatchers("/favicon.ico").permitAll()
                        .mvcMatchers("/views/").permitAll()
                        .mvcMatchers("/api/**").hasAuthority(Authority.USER.name())
                        .anyRequest().authenticated());
        // Session 기반의 인증기반을 사용하지 않고 추후 JWT를 이용하여서 인증 예정
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // Spring Security Custom Filter Load - Form '인증'에 대해서 사용
        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
