package com.albanoi;

import java.util.Optional;

public class CommandResult<T> {

    private final Optional<T> result;

    public CommandResult(T result) {
        this.result = Optional.ofNullable(result);
    }

    public static CommandResult noResult() {
        return new CommandResult(null);
    }

    public static <P> CommandResult<P> of(P t){
        return new CommandResult(t);
    }

    public boolean hasResult() {
        return this.result.isPresent();
    }

    public T getResult() {
        return result.orElseThrow();
    }


}
