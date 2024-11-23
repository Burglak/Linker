package com.burglak.linker.exception;

public class PostReactionNotFoundException extends RuntimeException {
    public PostReactionNotFoundException(String message) {
        super(message);
    }

    public PostReactionNotFoundException(Long id) {super("PostReaction not found with id: " + id);}
}
