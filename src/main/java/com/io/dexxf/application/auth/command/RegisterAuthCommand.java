package com.io.dexxf.application.auth.command;

import com.io.dexxf.domain.auth.valueobject.Roles;

public record RegisterAuthCommand (String userId , String username, String email , String password){
}
