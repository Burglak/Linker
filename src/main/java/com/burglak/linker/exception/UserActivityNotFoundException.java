package com.burglak.linker.exception;

public class UserActivityNotFoundException extends RuntimeException {
  public UserActivityNotFoundException(String message) {
    super(message);
  }

  public UserActivityNotFoundException(Long id) {
    super("UserActivity not found with id: " + id);
  }
}
