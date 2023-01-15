package com.albanoi.spring.gateway.exceptions;

public class BaseAlbanoiException extends RuntimeException {

    public BaseAlbanoiException(AlbanoiErrors errorCode, String message) {
        super("""
                 | ERR %s | %s
                """.formatted(errorCode.getErrorCode(), message));
    }

}
