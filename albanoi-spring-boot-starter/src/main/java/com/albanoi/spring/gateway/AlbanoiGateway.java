package com.albanoi.spring.gateway;

import com.albanoi.Command;
import com.albanoi.CommandResult;

public interface AlbanoiGateway {
    <R, C extends Command> CommandResult<R> execute(C command, Class<R> resultType);
}
