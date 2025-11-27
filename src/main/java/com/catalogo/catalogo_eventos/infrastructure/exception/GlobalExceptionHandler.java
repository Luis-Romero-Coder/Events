package com.catalogo.catalogo_eventos.infrastructure.exception;

import com.catalogo.catalogo_eventos.domain.exception.BusinessRuleException;
import com.catalogo.catalogo_eventos.domain.exception.DomainValidationException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ApiError> handleBusinessRule(BusinessRuleException ex){
        ApiError err = new ApiError(LocalDateTime.now(), HttpStatus.CONFLICT.value(), "Validation error", List.of(ex.getMessage()), "");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }

    @ExceptionHandler(DomainValidationException.class)
    public ResponseEntity<ApiError> handleDomainValidation(DomainValidationException ex) {
        ApiError err = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Validation error", List.of(ex.getMessage()), "");
        return ResponseEntity.badRequest().body(err);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraint(ConstraintViolationException ex) {
        List<String> msgs = ex.getConstraintViolations().stream().map(cv -> cv.getMessage()).collect(Collectors.toList());
        ApiError err = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Validation error", msgs, "");
        return ResponseEntity.badRequest().body(err);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        ApiError err = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Type mismatch", List.of(ex.getMessage()), "");
        return ResponseEntity.badRequest().body(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(Exception ex) {
        ApiError err = new ApiError(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal error", List.of(ex.getMessage()), "");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }
}
