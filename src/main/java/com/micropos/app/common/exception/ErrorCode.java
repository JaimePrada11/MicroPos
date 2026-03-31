package com.micropos.app.common.exception;

public enum ErrorCode {

    RESOURCE_NOT_FOUND(404, "Resource not found"),

    CONFLICT(409, "Conflict"),

    BAD_REQUEST(400, "Bad request"),

    UNAUTHORIZED(401, "Unauthorized access"),

    INTERNAL_ERROR(500, "Unexpected internal error");

    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}