package com.io.dexxf.domain.auth.factory;

import com.io.dexxf.domain.auth.entity.Auth;
import com.io.dexxf.domain.auth.valueobject.Email;
import com.io.dexxf.domain.auth.valueobject.HashedPassword;

public interface AuthFactory {
    Auth create(String userId , String username,Email email ,HashedPassword password);
}
