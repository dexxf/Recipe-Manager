package com.io.dexxf.domain.auth.result;

public enum ValidateAuthRegistrationResult {
    USERID_NOT_FOUND("User ID does not exist."),
    USERNAME_TAKEN("Username is already taken."),
    INVALID_EMAIL_FORMAT("Email format is invalid."),
    EMAIL_NULL_OR_EMPTY("Email cannot be null or empty."),
    SUCCESS("Validation successful.");

    private final String message;

    ValidateAuthRegistrationResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
