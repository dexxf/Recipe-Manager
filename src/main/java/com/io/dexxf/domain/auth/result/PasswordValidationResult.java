package com.io.dexxf.domain.auth.result;

public enum PasswordValidationResult {
    NULL_OR_EMPTY_HASHED_PASSWORD("Hashed password cannot be null or empty."),
    INVALID_HASH_FORMAT("Invalid hashed password format."),
    INVALID_HASH_LENGTH("Invalid BCRYPT hash length."),
    INVALID_RAW_PASSWORD_LENGTH("Password must be at least 8 characters long.");

    private final String message;

    PasswordValidationResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
