package com.albanoi.spring.gateway.exceptions;

import com.albanoi.Command;

public class MissingHandlerException extends BaseAlbanoiException{

    public MissingHandlerException(Command command) {
        super(AlbanoiErrors.MISSING_HANDLER, """
                There is no handler registered for %s
                """.formatted(command));
    }
}
