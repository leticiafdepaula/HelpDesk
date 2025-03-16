package com.leticia.helpDesk.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class )
    public ResponseEntity<StandarError> objectNotFoundException(ObjectNotFoundException ex,
     HttpServletRequest request) {
     StandarError error = new StandarError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Object Not found", ex.getMessage(), request.getRequestURI());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}
