package com.io.dexxf.domain.auth.factory;

import com.io.dexxf.common.util.IDGenerator;
import com.io.dexxf.domain.auth.entity.Auth;
import com.io.dexxf.domain.auth.valueobject.Email;
import com.io.dexxf.domain.auth.valueobject.HashedPassword;
import com.io.dexxf.domain.auth.valueobject.Roles;

import java.time.Clock;
import java.time.LocalDateTime;

public class DefaultAuthFactory implements AuthFactory{
    private final IDGenerator idGenerator;
    private final Clock clock;

    public DefaultAuthFactory (IDGenerator idGenerator, Clock clock) {
        this.idGenerator = idGenerator;
        this.clock = clock;
    }

    @Override
    public Auth create(String userId , String username,Email email ,HashedPassword password) {
        return new Auth(idGenerator.generate(),userId,username,email,password,LocalDateTime.now(clock),Roles.USER);
    }
}
