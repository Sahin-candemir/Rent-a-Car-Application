package com.shn.reservation.dto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> handle(ResourceNotFoundException exception){
        return new ResponseEntity<>(exception.getErrorMessage(), HttpStatus.resolve(exception.getErrorMessage().status()));
    }
    @ExceptionHandler(VehicleInUseException.class)
    public ResponseEntity<ErrorMessage> handle(VehicleInUseException exception){
        return new ResponseEntity<>(exception.getErrorMessage(), HttpStatus.resolve(exception.getErrorMessage().status()));
    }
    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<?> handle(ReservationNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

}
