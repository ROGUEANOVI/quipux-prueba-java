package com.quipux.prueba_java.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message, String name) {
        super(String.format(message, name));
    }
}
