package com.io.dexxf.domain.auth.valueobject;

import com.io.dexxf.common.result.Result;
import com.io.dexxf.domain.auth.result.PasswordValidationResult;

public final class HashedPassword {

    private final String hashedPassword;

    private HashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public static Result<HashedPassword, PasswordValidationResult> of(String hashedPassword) {
        if (hashedPassword == null || hashedPassword.isEmpty()) {
            return Result.fail(PasswordValidationResult.NULL_OR_EMPTY_HASHED_PASSWORD);
        }

        if (!hashedPassword.startsWith("$2a$") && !hashedPassword.startsWith("$2b$") && !hashedPassword.startsWith("$2y$")) {
            return Result.fail(PasswordValidationResult.INVALID_HASH_FORMAT);
        }

        if (hashedPassword.length() != 60) {
            return Result.fail(PasswordValidationResult.INVALID_HASH_LENGTH);
        }

        return Result.ok(new HashedPassword(hashedPassword));
    }

    public String get() {
        return hashedPassword;
    }

    @Override
    public String toString() {
        return "[PRIVATE]";
    }

}
