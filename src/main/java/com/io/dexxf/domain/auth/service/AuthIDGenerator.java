package com.io.dexxf.domain.auth.service;

import com.io.dexxf.common.util.IDGenerator;
import com.io.dexxf.domain.auth.repository.AuthDomainRepository;

import java.util.UUID;

public class AuthIDGenerator implements IDGenerator {

    private final AuthDomainRepository repository;

    public AuthIDGenerator (AuthDomainRepository repository) {
        this.repository = repository;
    }

    @Override
    public String generate() {
        String authId = "";

        do {
            authId = "@WT"+ UUID.randomUUID().toString().toUpperCase().substring(0,7);
        }while(repository.authIdExist(authId));

        return authId;
    }
}
