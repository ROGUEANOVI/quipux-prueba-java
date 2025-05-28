package com.quipux.prueba_java.exception;

import java.util.List;
import java.util.Map;

public class PlaylistBadRequestException extends RuntimeException {

    private final List<Map<String, String>> errors;

    public PlaylistBadRequestException(List<Map<String, String>> errors) {
        this.errors = errors;
    }

    public List<Map<String, String>> getErrors() {
        return errors;
    }
}
