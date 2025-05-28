package com.quipux.prueba_java.exceptionhandler;

import com.quipux.prueba_java.constant.Messages;
import com.quipux.prueba_java.constant.Values;
import com.quipux.prueba_java.exception.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {

        Map<String, String> errors = new HashMap<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            String[] pathElements = violation.getPropertyPath().toString().split(Messages.SPLIT_REGEX);
            String paramName = pathElements[pathElements.length - Values.LAST_ELEMENT_INDEX];
            String message = violation.getMessage();
            errors.put(paramName, message);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(PlaylistBadRequestException.class)
    public ResponseEntity<Map<String, String>> handleBadRequestException(PlaylistBadRequestException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getErrors().forEach(error -> errors.put(error.keySet().iterator().next(), error.values().iterator().next()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(PlaylistNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlePlaylistNotFoundException(PlaylistNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap(Messages.MESSAGE, ex.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(UserNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap(Messages.MESSAGE, ex.getMessage()));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap(Messages.MESSAGE, ex.getMessage()));
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleRoleNotFoundException(RoleNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap(Messages.MESSAGE, ex.getMessage()));
    }
}
