package com.codewithope.timetrackingapi.exception;

import com.codewithope.timetrackingapi.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
       ErrorResponse response = ErrorResponse.builder()
               .message(ex.getMessage())
               .timestamp(Instant.now())
               .statusCode(404)
               .build();
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }
}
