package com.burglak.linker.exception;

public class PostCommentNotFoundException extends RuntimeException {
    public PostCommentNotFoundException(String message) {
        super(message);
    }

    public PostCommentNotFoundException(Long id) {super("PostComment not found with id: " + id);}

}
