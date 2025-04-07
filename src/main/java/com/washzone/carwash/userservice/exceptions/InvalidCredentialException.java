package com.washzone.carwash.userservice.exceptions;

public class InvalidCredentialException extends RuntimeException{
    public InvalidCredentialException(String message){
        super(message);

    }
}
