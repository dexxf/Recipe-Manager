package com.io.dexxf.application.auth.dto;

public record RegisterAuthDTO(boolean success, String authId, String message) {

    public static RegisterAuthDTO ok(String authId) {
        return new RegisterAuthDTO(true, authId, null);
    }

    public static RegisterAuthDTO fail(String message) {
        return new RegisterAuthDTO(false, null, message);
    }
}
