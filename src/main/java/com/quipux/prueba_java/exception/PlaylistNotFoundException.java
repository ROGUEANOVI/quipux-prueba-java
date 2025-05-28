package com.quipux.prueba_java.exception;

public class PlaylistNotFoundException extends RuntimeException {

    public PlaylistNotFoundException(String message, String name) {
        super(String.format(message, name));
    }
}
