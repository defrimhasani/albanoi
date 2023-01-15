package com.albanoi.spring.gateway.exceptions;

public enum AlbanoiErrors {

    MULTIPLE_HANDLERS_FOR_COMMAND(1, "Multiple handlers are registered for the given command"),
    MISSING_HANDLER(2, "There is no handler for the given command");

    private final int errorCode;
    private final String description;

    AlbanoiErrors(int errorCode, String description) {

        this.errorCode = errorCode;
        this.description = description;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getDescription() {
        return description;
    }


}
