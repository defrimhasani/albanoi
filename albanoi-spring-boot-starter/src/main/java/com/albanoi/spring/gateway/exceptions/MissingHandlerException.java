package com.albanoi.spring.gateway.exceptions;

import com.albanoi.Command;
import org.albanoi.Query;

public class MissingHandlerException extends BaseAlbanoiException{

    public MissingHandlerException(Command command) {
        super(AlbanoiErrors.MISSING_HANDLER, """
                There is no handler registered for %s
                """.formatted(command));
    }

    public MissingHandlerException(Query query){
        super(AlbanoiErrors.MISSING_HANDLER, """
                There is no handler registered for %s
                """.formatted(query));
    }
}
