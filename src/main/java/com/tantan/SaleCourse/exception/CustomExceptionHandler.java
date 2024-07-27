package com.tantan.SaleCourse.exception;


import com.tantan.SaleCourse.response.baseresponse.BaseDataResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<BaseDataResponse> handleCustomException(CustomException ex) {
        BaseDataResponse dataResponse = ex.getDataResponse();
        return new ResponseEntity<>(dataResponse, HttpStatus.valueOf(dataResponse.getHttpStatusCode()));
    }
}
