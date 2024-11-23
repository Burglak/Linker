package com.burglak.linker.exception;

public class UserImageNotFoundException extends RuntimeException {
    public UserImageNotFoundException(String message) {
        super(message);
    }

    public UserImageNotFoundException(Long id) {super("UserImage not found with id: " + id);}
}
