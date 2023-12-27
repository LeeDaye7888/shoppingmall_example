package com.example.member.global.config.security.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class SecurityUser extends User {

    private final Long id;
    private final String username;
    public SecurityUser(Long id, String username, String password, Collection<GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
        this.username = username;
    }
}
