package com.albanoi.spring.gateway;

import com.albanoi.Command;
import com.albanoi.CommandHandler;
import com.albanoi.CommandResult;
import com.albanoi.spring.gateway.exceptions.MissingHandlerException;
import com.albanoi.spring.gateway.exceptions.MultipleHandlersForCommandException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.ResolvableType;
import org.springframework.util.StopWatch;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class DefaultAlbanoiGateway implements AlbanoiGateway {

    @Autowired
    private ApplicationContext applicationContext;

    Logger logger = LoggerFactory.getLogger(DefaultAlbanoiGateway.class);

    @Override
    public <R, C extends Command> CommandResult<R> execute(C command, Class<R> resultType) {

        StopWatch stopWatch = new StopWatch();

        if (logger.isTraceEnabled()) {
            stopWatch.start();
        }

        logger.trace("Looking for registered command handlers for the following generic parameters {}, {}", command.getClass(), resultType);

        var registeredHandlerNames = applicationContext.getBeanNamesForType(
                ResolvableType.forClassWithGenerics(CommandHandler.class, command.getClass(), resultType)
        );

        if (registeredHandlerNames.length == 0) {
            logger.error("Looks like there is no handler registered for {}", command.getClass());
            throw new MissingHandlerException(command);
        }

        if (registeredHandlerNames.length > 1) {
            logger.error("Looks like on your application there are multiple command handlers for {}", command.getClass());
            throw new MultipleHandlersForCommandException(command, registeredHandlerNames);
        }

        var handlerName = registeredHandlerNames[0];

        logger.trace("Fine, there is only one handler registered and that is {}", handlerName);

        CommandHandler<C, R> handler = (CommandHandler<C, R>) applicationContext.getBean(handlerName);

        CommandResult<R> executionResult = handler.execute(command);

        if (logger.isTraceEnabled()) {
            stopWatch.stop();
        }

        logger.trace("Execution of '{}' took {} millis", command, stopWatch.getTotalTimeMillis());

        return executionResult;
    }

}
