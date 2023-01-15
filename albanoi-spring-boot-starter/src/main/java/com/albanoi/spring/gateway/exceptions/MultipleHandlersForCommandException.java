package com.albanoi.spring.gateway.exceptions;

import com.albanoi.Command;

import java.util.Arrays;

public class MultipleHandlersForCommandException extends BaseAlbanoiException {

    public MultipleHandlersForCommandException(Command command, String[] registeredHandlerNames) {
        super(AlbanoiErrors.MULTIPLE_HANDLERS_FOR_COMMAND, """
                Following handlers %s are registered for the '%s' command. Make sure you have one handler per command
                """.formatted(Arrays.toString(registeredHandlerNames), command.getClass().getSimpleName()));
    }
}
