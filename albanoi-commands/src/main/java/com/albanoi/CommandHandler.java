package com.albanoi;

@FunctionalInterface
public interface CommandHandler<C extends Command, R> {

    CommandResult<R> execute(C command);
}

