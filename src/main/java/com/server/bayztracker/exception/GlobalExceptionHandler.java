package com.server.bayztracker.exception;

import com.server.bayztracker.entity.ErrorDetail;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ RuntimeException.class})
    public ResponseEntity<Object> handleUnsupportedCurrency(Exception ex) {
        ErrorDetail detail = new ErrorDetail();
        detail.setMessage(ex.getMessage());
        detail.setStatusCode(String.valueOf(HttpStatus.FORBIDDEN));
        return new ResponseEntity<>(
                detail, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({ Exception.class})
    public ResponseEntity<Object> handleException(Exception ex) {
        ErrorDetail detail = new ErrorDetail();
        detail.setMessage(ex.getMessage());
        detail.setStatusCode(String.valueOf(HttpStatus.FORBIDDEN));
        return new ResponseEntity<>(
                detail, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }
}
