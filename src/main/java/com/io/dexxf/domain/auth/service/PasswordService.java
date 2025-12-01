package com.io.dexxf.domain.auth.service;

import com.io.dexxf.common.result.Result;
import com.io.dexxf.domain.auth.result.PasswordValidationResult;
import com.io.dexxf.domain.auth.valueobject.HashedPassword;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {
    private final PasswordHasher hasher;

    public PasswordService(PasswordHasher hasher) {
        this.hasher = hasher;
    }

    public Result<HashedPassword, PasswordValidationResult> createHashedPassword(String rawPassword) {
        if(rawPassword.length() < 8) return Result.fail(PasswordValidationResult.INVALID_RAW_PASSWORD_LENGTH);
        String hashed = hasher.encode(rawPassword);

        return HashedPassword.of(hashed);
    }
}
