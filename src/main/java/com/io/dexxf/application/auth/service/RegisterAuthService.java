package com.io.dexxf.application.auth.service;

import com.io.dexxf.application.auth.command.RegisterAuthCommand;
import com.io.dexxf.application.auth.dto.RegisterAuthDTO;
import com.io.dexxf.application.auth.port.out.AuthAppRepository;
import com.io.dexxf.common.result.Result;
import com.io.dexxf.domain.auth.entity.Auth;
import com.io.dexxf.domain.auth.result.ValidateAuthRegistrationResult;
import com.io.dexxf.domain.auth.service.PasswordService;
import com.io.dexxf.domain.auth.service.ValidateAuthRegistration;
import com.io.dexxf.domain.auth.valueobject.HashedPassword;
import org.springframework.stereotype.Service;

@Service
public final class RegisterAuthService {

    private final PasswordService passwordService;
    private final ValidateAuthRegistration validateAuthRegistration;
    private final AuthAppRepository repository;

    public RegisterAuthService (PasswordService passwordService, ValidateAuthRegistration validateAuthRegistration, AuthAppRepository repository) {
        this.passwordService = passwordService;
        this.validateAuthRegistration = validateAuthRegistration;
        this.repository = repository;
    }

    public RegisterAuthDTO execute(RegisterAuthCommand command) {

        if (command.userId() == null || command.userId().isBlank()) {
            return RegisterAuthDTO.fail("User ID cannot be null or empty");
        }
        if (command.username() == null || command.username().isBlank()) {
            return RegisterAuthDTO.fail("Username cannot be null or empty");
        }
        if (command.email() == null || command.email().isBlank()) {
            return RegisterAuthDTO.fail("Email cannot be null or empty");
        }
        if (command.password() == null || command.password().isBlank()) {
            return RegisterAuthDTO.fail("Password cannot be null or empty");
        }

        var passwordHashing = passwordService.createHashedPassword(command.password());
        if(!passwordHashing.success()) return RegisterAuthDTO.fail(passwordHashing.errorMsg().getMessage());
        HashedPassword hashedPassword = passwordHashing.data();

        Result<Auth, ValidateAuthRegistrationResult> getAuth = validateAuthRegistration.check(command.userId(),
                command.username(), command.email(),hashedPassword);

        if(!getAuth.success()) return RegisterAuthDTO.fail(getAuth.errorMsg().getMessage());
        Auth auth = getAuth.data();

        repository.save(auth);

        return RegisterAuthDTO.ok(auth.getAuthId());
    }


}
