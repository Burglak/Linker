package com.burglak.linker.auth;

public class TokenNotFoundException extends RuntimeException {
    public TokenNotFoundException(String token) {
        super("Token not found: " + token);
    }
}
