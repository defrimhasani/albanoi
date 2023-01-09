package com.albanoi;

public interface CommandHandler<C extends Command, R> {

    CommandResult<R> execute(C command);
}

