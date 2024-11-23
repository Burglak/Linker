package com.burglak.linker.exception;

public class PostReportNotFoundException extends RuntimeException {
    public PostReportNotFoundException(String message) {
        super(message);
    }

    public PostReportNotFoundException(Long id) {super("PostReport not found with id: " + id);}
}
