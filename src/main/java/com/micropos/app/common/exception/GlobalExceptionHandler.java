package com.micropos.app.common.exception;

import com.micropos.app.common.dto.ErrorResponse;
import com.micropos.app.common.dto.FieldError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException ex, HttpServletRequest request) {

        ErrorCode code = ex.getErrorCode();

        ErrorResponse error = ErrorResponse.of(
                code.getStatus(),
                code.name(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(code.getStatus()).body(error);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {

        List<FieldError> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> new FieldError(
                        err.getField(),
                        err.getDefaultMessage() ))
                .toList();

        ErrorResponse error = ErrorResponse.of(
                400,
                "VALIDATION_ERROR",
                "Validation failed",
                request.getRequestURI(),
                errors
        );

        return  ResponseEntity.badRequest().body(error);

    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(
            AccessDeniedException ex,
            HttpServletRequest request) {

        ErrorResponse error = ErrorResponse.of(
                403,
                "ACCESS_DENIED",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        ErrorResponse error = ErrorResponse.of(
                500,
                "INTERNAL_ERROR",
                "Unexpected error occurred",
                request.getRequestURI()
        );

        return  ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }
}
