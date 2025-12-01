package com.io.dexxf.domain.auth.service;

import com.io.dexxf.common.result.Result;
import com.io.dexxf.domain.auth.entity.Auth;
import com.io.dexxf.domain.auth.factory.AuthFactory;
import com.io.dexxf.domain.auth.repository.AuthDomainRepository;
import com.io.dexxf.domain.auth.result.EmailResult;
import com.io.dexxf.domain.auth.result.ValidateAuthRegistrationResult;
import com.io.dexxf.domain.auth.valueobject.Email;
import com.io.dexxf.domain.auth.valueobject.HashedPassword;

import static com.io.dexxf.domain.auth.result.ValidateAuthRegistrationResult.*;

public final class ValidateAuthRegistration {
    private final AuthFactory factory;
    private final AuthDomainRepository repository;

    public ValidateAuthRegistration (AuthFactory factory, AuthDomainRepository repository) {
        this.factory = factory;
        this.repository = repository;
    }

    public Result<Auth, ValidateAuthRegistrationResult> check(String userId , String username, String email , HashedPassword password) {
            var validationResult = validate(userId,username);
            if(validationResult != SUCCESS) return Result.fail(validationResult);

            Result<Email, EmailResult> emailResult = Email.of(email);

            if(!emailResult.success()) {
                if(emailResult.errorMsg() == EmailResult.EMAIL_NULL_OR_EMPTY) return Result.fail(EMAIL_NULL_OR_EMPTY);
                if(emailResult.errorMsg() == EmailResult.INVALID_EMAIL_FORMAT) return Result.fail(INVALID_EMAIL_FORMAT);
            }

            return Result.ok(factory.create(userId,username, emailResult.data(),password), SUCCESS);
    }

    private ValidateAuthRegistrationResult validate(String userId, String username) {
        if(!repository.userIdExist(userId)) return USERID_NOT_FOUND;
        if(repository.usernameTaken(username)) return USERNAME_TAKEN;
        return SUCCESS;
    }


}
