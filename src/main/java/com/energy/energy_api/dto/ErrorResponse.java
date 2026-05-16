package com.energy.energy_api.dto;

public class ErrorResponse {

    private final String error;
    private final String message;
    private final String details;

    public ErrorResponse(String error, String message, String details) {
        this.error = error;
        this.message = message;
        this.details = details;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
