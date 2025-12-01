package com.io.dexxf.domain.auth.service;

public interface PasswordHasher {
    String encode(CharSequence rawPassword);
    boolean matches(CharSequence rawPassword, String hashedPassword);
}
