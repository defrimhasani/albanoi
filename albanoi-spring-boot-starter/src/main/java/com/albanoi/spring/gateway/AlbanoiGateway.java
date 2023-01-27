package com.albanoi.spring.gateway;

import com.albanoi.Command;
import com.albanoi.CommandResult;
import org.albanoi.Query;

public interface AlbanoiGateway {
    <R, C extends Command> CommandResult<R> execute(C command, Class<R> resultType);

    <Q extends Query, R> R handle(Q query, Class<R> resultType);
}
