package com.builders.clientshandler.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@ControllerAdvice
@RestController
public class ExceptionHandlerController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.error("Method Argument Not Valid Exception: {}", ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleValidationHttpNotFoundExceptions(ResponseStatusException ex) {
        log.error("Response Status Exception: {}", ex.getReason());
        return new ResponseEntity<>(ex.getReason(), Objects.requireNonNull(HttpStatus.resolve(ex.getStatus().value())));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        log.error("Response Status Exception: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), Objects.requireNonNull(HttpStatus.UNPROCESSABLE_ENTITY));
    }

}
