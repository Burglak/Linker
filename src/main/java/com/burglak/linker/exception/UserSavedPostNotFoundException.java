package com.burglak.linker.exception;

public class UserSavedPostNotFoundException extends RuntimeException {
    public UserSavedPostNotFoundException(String message) {
        super(message);
    }

    public UserSavedPostNotFoundException(Long id) {super("UserSavedPost not found with id: " + id);}
}
