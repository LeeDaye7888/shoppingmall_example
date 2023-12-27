package com.example.member.global.config.security;

import com.example.member.global.config.security.model.SecurityUser;
import com.example.member.global.config.security.model.TokenInfo;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Configuration
public class JwtTokenProvider {

    private final Long EXPIRATION_MILLISECONDS = 1000 * 60 * 30L;

    private SecretKey key;

    public JwtTokenProvider(@Value(value = "${jwt.secret}") String secret) {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public TokenInfo createToken(Authentication authentication) {
        var authorities = authentication.getAuthorities()
                .stream()
                .map((x) -> x.getAuthority())
                .collect(Collectors.joining(","));

        var now = new Date();
        var accessExpiration = new Date(now.getTime() + EXPIRATION_MILLISECONDS);

        var principal = ((SecurityUser) authentication.getPrincipal());
        var member = principal.getId();

        var accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .claim("memberId", member)
                .setIssuedAt(now)
                .setExpiration(accessExpiration)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return new TokenInfo("Bearer", accessToken);
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Authentication getAuthentication(String token)  {
        var claims = getClaims(token);
        var auth = claims.get("auth", String.class);
        var memberId = claims.get("memberId", Long.class);

        var authorities = Arrays.stream(auth.split(","))
                .map(x -> new SimpleGrantedAuthority(x))
                .map(authority -> (GrantedAuthority) authority)
                .collect(Collectors.toList());

        var principal = new SecurityUser(memberId, claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            log.warn("message: %s".formatted(e.getLocalizedMessage()), e);
            if (e instanceof SecurityException) {
                // Invalid JWT Token
            } else if (e instanceof MalformedJwtException ) {
                // Invalid JWT Token
            } else if (e instanceof ExpiredJwtException ) {
                // Expired JWT Token
            } else if (e instanceof UnsupportedJwtException ) {
                // Unsupported JWT Token
            } else if (e instanceof IllegalArgumentException ) {
                // JWT claims string is empty
            }
        }
        return false;
    }
}
