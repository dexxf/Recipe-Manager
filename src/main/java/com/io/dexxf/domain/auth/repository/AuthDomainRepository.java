package com.io.dexxf.domain.auth.repository;

public interface AuthDomainRepository {
    boolean authIdExist(String authId);
    boolean userIdExist(String userId);
    boolean usernameTaken(String username);
}
