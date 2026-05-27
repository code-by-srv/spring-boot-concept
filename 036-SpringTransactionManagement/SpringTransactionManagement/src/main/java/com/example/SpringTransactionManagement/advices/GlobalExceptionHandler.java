package com.example.SpringTransactionManagement.advices;

import com.example.SpringTransactionManagement.exceptions.ResourceNotFoundException;
import org.hibernate.StaleObjectStateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(StaleObjectStateException.class)
    public ResponseEntity<?> handleStateObjectState(StaleObjectStateException ex) {
        return new ResponseEntity<>("Stale data", HttpStatus.CONFLICT);
    }
}
