package com.io.dexxf.adapter.auth.out.security;

import com.io.dexxf.application.auth.port.out.AuthAppRepository;
import com.io.dexxf.domain.auth.entity.Auth;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthAppRepository authAppRepository;

    public CustomUserDetailsService (AuthAppRepository authAppRepository) {
        this.authAppRepository = authAppRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Auth> authOptional = authAppRepository.findByUsername(username);

        if(authOptional.isEmpty()) throw new UsernameNotFoundException("Username not found.");

        return new UserPrincipal(authOptional.get());
    }
}
