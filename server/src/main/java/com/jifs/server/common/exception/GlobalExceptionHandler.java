package com.jifs.server.common.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;


import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountException.class)
    public ResponseEntity<AppError> handleAccountException(AccountException exception, HttpServletRequest req) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, exception.getMessage(), exception, req);
    }

    // Error handling on @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppError> handleValidationErrors(MethodArgumentNotValidException exception, HttpServletRequest req) {
        List<String> errorMessages = exception.getBindingResult().getAllErrors().stream()
                .map(objectError -> {
                    if (objectError instanceof FieldError fieldError) {
                        return fieldError.getField() + ": " + fieldError.getDefaultMessage();
                    }
                    return objectError.getDefaultMessage();
                })
                .toList();

        return buildResponseEntity(HttpStatus.BAD_REQUEST, String.valueOf(errorMessages), exception, req);
    }

    // Handling security-related errors
    @ExceptionHandler(AccountStatusException.class)
    public ResponseEntity<AppError> handleAccountStatusException(AccountStatusException exception, HttpServletRequest req) {
        return buildResponseEntity(HttpStatus.FORBIDDEN,"The account is locked", exception, req);
    }

    // Handling invalid JWT signature errors
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<AppError> handleAccessDeniedException(AccessDeniedException exception, HttpServletRequest req) {
        return buildResponseEntity(HttpStatus.FORBIDDEN,"You are not authorized to access this resource", exception, req);
    }

    // Handling invalid JWT signature errors
    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<AppError> handleSignatureException(SignatureException exception, HttpServletRequest req) {
        return buildResponseEntity(HttpStatus.FORBIDDEN,"The JWT signature is invalid", exception, req);
    }

    // JWT Expired
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<AppError> handleExpiredJwtException(ExpiredJwtException exception, HttpServletRequest req) {
        return buildResponseEntity(HttpStatus.FORBIDDEN,"The JWT token has expired", exception, req);
    }

    // Handling all other unknown exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<AppError> handleGenericException(Exception exception, HttpServletRequest req) {
        // TODO envoyer cette stack trace à un outil d'observabilité
        exception.printStackTrace();

        return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR,"Unknown internal server error.", exception, req);
    }

    private ResponseEntity<AppError> buildResponseEntity(
            HttpStatus status, String description,  Exception e, HttpServletRequest req) {
        final var appError =
                new AppError(status, description, e.getMessage(), e.getLocalizedMessage(), req.getRequestURI());
        return new ResponseEntity<>(appError, appError.getHttpStatus());
    }
}
