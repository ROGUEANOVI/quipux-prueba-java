package com.quipux.prueba_java.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String message, String field) {
        super(String.format(message, field));
    }
}
