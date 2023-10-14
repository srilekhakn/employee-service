package com.srilekha.employeeservice.controller;

import com.srilekha.employeeservice.model.dto.ErrorResponseDTO;
import com.srilekha.employeeservice.model.exception.NotFoundException;
import com.srilekha.employeeservice.model.exception.RecordAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<ErrorResponseDTO> handleIllegalArgument(final IllegalArgumentException ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ErrorResponseDTO> handleNotFound(final NotFoundException ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(SecurityException.class)
    public final ResponseEntity<ErrorResponseDTO> handleSecurity(final SecurityException ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    @ExceptionHandler(RecordAlreadyExistException.class)
    public final ResponseEntity<ErrorResponseDTO> handleRecordExists(final RecordAlreadyExistException ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(ex.getMessage());
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(errorResponse);
    }

}