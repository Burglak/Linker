package com.burglak.linker.exception;

public class UserStatusNotFoundException extends RuntimeException {
    public UserStatusNotFoundException(String message) {
        super(message);
    }

    public UserStatusNotFoundException(Long id) {super("UserStatus not found with id: " + id);}
}
