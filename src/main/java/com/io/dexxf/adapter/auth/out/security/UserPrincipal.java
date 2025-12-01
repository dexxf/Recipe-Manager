package com.io.dexxf.adapter.auth.out.security;

import com.io.dexxf.domain.auth.entity.Auth;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {
    private final Auth auth;

    public UserPrincipal (Auth auth) {
        this.auth = auth;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + auth.getRoles().name()));
    }

    @Override
    public String getPassword() {
        return auth.getPassword().get();
    }

    @Override
    public String getUsername() {
        return auth.getUsername();
    }
}
