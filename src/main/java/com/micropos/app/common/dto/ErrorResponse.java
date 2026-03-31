package com.micropos.app.common.dto;

import java.time.Instant;
import java.util.List;

public record ErrorResponse(
        boolean success,
        int status,
        String errorCode,
        String message,
        String path,
        List<FieldError> fieldErrors,
        Instant timestamp
) {
    public static ErrorResponse of(
            int status,
            String errorCode,
            String message,
            String path
    ) {
        return new ErrorResponse(
                false,
                status,
                errorCode,
                message,
                path,
                null,
                Instant.now()
        );
    }

    public static ErrorResponse of(
            int status,
            String errorCode,
            String message,
            String path,
            List<FieldError> errors
    ) {
        return new ErrorResponse(
                false,
                status,
                errorCode,
                message,
                path,
                errors !=null ? errors : List.of(),
                Instant.now()
        );
    }
}


