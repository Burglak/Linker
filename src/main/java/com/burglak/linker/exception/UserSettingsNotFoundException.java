package com.burglak.linker.exception;

public class UserSettingsNotFoundException extends RuntimeException {
    public UserSettingsNotFoundException(String message) {
        super(message);
    }

    public UserSettingsNotFoundException(Long id) {super("UserSettings not found with id: " + id);}
}
