package com.washzone.carwash.userservice.exceptions;

public class InvalidJwtTokenException extends RuntimeException {
    public InvalidJwtTokenException(String message) {
        super(message);
    }

}
