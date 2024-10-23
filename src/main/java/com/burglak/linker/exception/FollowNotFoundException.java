package com.burglak.linker.exception;

public class FollowNotFoundException extends RuntimeException {
    public FollowNotFoundException(String message) {
        super(message);
    }

    public FollowNotFoundException(Long id) {super("Follow not found with id: " + id);}
}
