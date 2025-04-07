package com.washzone.carwash.userservice.exceptions;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ErrorResponse {
    public String message;
    public LocalDateTime localDateTime;
    public int httpStatus;
    public ErrorResponse(String message, LocalDateTime localDateTime, int httpStatus) {
        this.message = message;
        this.localDateTime = localDateTime;
        this.httpStatus = httpStatus;
    }

    public ErrorResponse (String message) {
        this.message = message;
    }
}
