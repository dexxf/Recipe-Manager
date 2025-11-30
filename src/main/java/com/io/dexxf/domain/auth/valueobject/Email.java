package com.io.dexxf.domain.auth.valueobject;

import com.io.dexxf.common.result.Result;
import com.io.dexxf.domain.auth.result.EmailResult;

import java.util.regex.Pattern;

public class Email {

    private final String email;

    private Email(String email) {
        this.email = email;
    }

    public static Result<Email, EmailResult> of(String email) {
        if(email == null || email.isEmpty())
            return Result.fail(EmailResult.EMAIL_NULL_OR_EMPTY);

        if (!Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$").matcher(email).matches())
            return Result.fail(EmailResult.INVALID_EMAIL_FORMAT);

        return Result.ok(new Email(email));
    }

    public String getEmail() {
        return email;
    }
}
