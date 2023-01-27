package com.albanoi.spring.gateway.exceptions;

import com.albanoi.Command;
import org.albanoi.Query;

import java.util.Arrays;

public class MultipleHandlersException extends BaseAlbanoiException {

    public MultipleHandlersException(Command command, String[] registeredHandlerNames) {
        super(AlbanoiErrors.MULTIPLE_HANDLERS_FOR_COMMAND, """
                Following handlers %s are registered for the '%s' command. Make sure you have one handler per command
                """.formatted(Arrays.toString(registeredHandlerNames), command.getClass().getSimpleName()));
    }

    public MultipleHandlersException(Query query, String[] registeredHandlerNames) {
        super(AlbanoiErrors.MULTIPLE_HANDLERS_FOR_QUERY, """
                Following handlers %s are registered for the '%s' query. Make sure you have one handler per query
                """.formatted(Arrays.toString(registeredHandlerNames), query.getClass().getSimpleName()));
    }
}
