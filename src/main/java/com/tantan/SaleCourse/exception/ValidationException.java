package com.tantan.SaleCourse.exception;

import com.tantan.SaleCourse.response.baseresponse.BaseDataResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ControllerAdvice
public class ValidationException {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<BaseDataResponse> handleCustomException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            errors.put(fieldName, errorMessage);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new BaseDataResponse(true, HttpStatus.BAD_REQUEST.value(), "Some field has not validation", errors)
        );
    }
}