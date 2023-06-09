package backend.week8.common.controller;

import backend.week8.common.dto.ErrorResponse;
import backend.week8.common.enums.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBindException(BindingResult bindingResult) {
        ErrorCode errorCode = ErrorCode.INVALID_REQUEST_PARAMETER_BIND;
        List<String> errorMessages = collectErrorMessages(bindingResult);
        ErrorResponse errorResponse = new ErrorResponse(errorCode.getCode(), errorMessages.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorCode.getStatus()));
    }

    private List<String> collectErrorMessages(BindingResult bindingResult) {
        return bindingResult.getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotAllowed(HttpRequestMethodNotSupportedException e) {
        ErrorCode errorCode = ErrorCode.METHOD_NOT_ALLOWED;
        ErrorResponse errorResponse = new ErrorResponse(errorCode.getCode(), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorCode.getStatus()));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchElementException(NoSuchElementException e) {
        ErrorCode errorCode = ErrorCode.NO_SUCH_ELEMENT;
        ErrorResponse errorResponse = new ErrorResponse(errorCode.getCode(), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorCode.getStatus()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException e) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED_USER;
        ErrorResponse errorResponse = new ErrorResponse(errorCode.getCode(), errorCode.getDescription());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorCode.getStatus()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        ErrorCode errorCode = ErrorCode.INVALID_ARGUMENT;
        ErrorResponse errorResponse = new ErrorResponse(errorCode.getCode(), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorCode.getStatus()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpectedException(Exception e) {
        ErrorCode errorCode = ErrorCode.UNEXPECTED_EXCEPTION;
        ErrorResponse errorResponse = new ErrorResponse(errorCode.getCode(), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorCode.getStatus()));
    }
}
