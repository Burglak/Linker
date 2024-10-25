package com.burglak.linker.exception;

public class PostImageNotFoundException extends RuntimeException {
    public PostImageNotFoundException(String message) {
        super(message);
    }

    public PostImageNotFoundException(Long id) {super("PostImage not found with id: " + id);}
}
