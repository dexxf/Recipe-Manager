package com.io.dexxf.application.auth.port.out;

import com.io.dexxf.domain.auth.entity.Auth;

import java.util.Optional;

public interface AuthAppRepository {
    Optional<Auth> findByUsername(String username);
    void save(Auth auth);
}
