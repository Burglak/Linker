package com.burglak.linker.exception;

public class FriendNotFoundException extends RuntimeException {
    public FriendNotFoundException(String message) {
        super(message);
    }

    public FriendNotFoundException(Long id) {super("Friend not found with id: " + id);}
}
