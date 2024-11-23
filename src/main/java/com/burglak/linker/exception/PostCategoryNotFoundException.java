package com.burglak.linker.exception;

public class PostCategoryNotFoundException extends RuntimeException {

    public PostCategoryNotFoundException(String message) {
        super(message);
    }

    public PostCategoryNotFoundException(Long id) {super("PostCategory not found with id: " + id);}

}
