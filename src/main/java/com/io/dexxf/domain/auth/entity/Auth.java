package com.io.dexxf.domain.auth.entity;

import com.io.dexxf.domain.auth.valueobject.Email;
import com.io.dexxf.domain.auth.valueobject.HashedPassword;
import com.io.dexxf.domain.auth.valueobject.Roles;

import java.time.LocalDateTime;

public final class Auth {
    private final String authId;
    private final String userId;
    private String username;
    private Email email;
    private HashedPassword password;
    private Roles roles;
    private final LocalDateTime createdAt;

    public Auth(String authId, String userId, String username,Email email ,HashedPassword password, LocalDateTime createdAt, Roles roles) {
        this.authId = authId;
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public HashedPassword getPassword() {
        return password;
    }

    public void setPassword(HashedPassword password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getAuthId() {
        return authId;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }
}
