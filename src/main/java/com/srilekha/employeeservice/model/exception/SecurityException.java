package com.srilekha.employeeservice.model.exception;

public class SecurityException extends RuntimeException {
    public SecurityException(String message) {
        super(message);
    }
}