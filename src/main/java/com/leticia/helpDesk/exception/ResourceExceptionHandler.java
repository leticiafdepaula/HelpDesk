package com.leticia.helpDesk.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class )
    public ResponseEntity<StandarError> objectNotFoundException(ObjectNotFoundException ex, HttpServletRequest request) {
     StandarError error = new StandarError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Object Not found", ex.getMessage(), request.getRequestURI());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class )
    public ResponseEntity<StandarError> dataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {
        StandarError error = new StandarError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "violação de dados", ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class )
    public ResponseEntity<ValidationError> validationErrors(MethodArgumentNotValidException ex, HttpServletRequest request) {
       ValidationError errors = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Validation Error","Erro na validação dos campos", request.getRequestURI());

       for (FieldError x: ex.getBindingResult().getFieldErrors()) {
            errors.addError(x.getField(), x.getDefaultMessage());
       }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}
