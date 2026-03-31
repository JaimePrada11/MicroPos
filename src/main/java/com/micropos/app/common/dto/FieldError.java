package com.micropos.app.common.dto;

public record FieldError(
        String field,
        String message
) {
    public static FieldError of(String field, String message) {
        return new FieldError(field, message);
    }
}
