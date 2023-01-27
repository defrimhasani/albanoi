package com.albanoi.spring.gateway;

import com.albanoi.Command;
import com.albanoi.CommandHandler;
import com.albanoi.CommandResult;
import com.albanoi.spring.gateway.exceptions.MissingHandlerException;
import com.albanoi.spring.gateway.exceptions.MultipleHandlersException;
import org.albanoi.Query;
import org.albanoi.QueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;

/**
 * The default gateway uses the @{@link ApplicationContext} in order to find registered handlers
 * <p>
 * If there are no handlers for the given command of query then the gateway will throw a {@link MissingHandlerException}
 * <p>
 * If there are multiple handlers, then an instance of {@link MultipleHandlersException} will be thrown
 * <p>
 * If the handler is found then the command will be executed using the {@link CommandHandler} interface
 */
public class DefaultAlbanoiGateway implements AlbanoiGateway {

    @Autowired
    private ApplicationContext applicationContext;

    private final Logger logger = LoggerFactory.getLogger(DefaultAlbanoiGateway.class);

    @Override
    public <R, C extends Command> CommandResult<R> execute(C command, Class<R> resultType) {

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
            throw new MultipleHandlersException(command, registeredHandlerNames);
        }

        var handlerName = registeredHandlerNames[0];

        logger.trace("Fine, there is only one handler registered and that is {}", handlerName);

        CommandHandler<C, R> handler = (CommandHandler<C, R>) applicationContext.getBean(handlerName);

        CommandResult<R> executionResult = handler.execute(command);

        return executionResult;
    }

    @Override
    public <Q extends Query, R> R handle(Q query, Class<R> resultType) {

        logger.trace("Looking for registered query handlers for the following query type {}", query.getClass().getSimpleName());

        var registeredQueryHandlers = applicationContext.getBeanNamesForType(
                ResolvableType.forClassWithGenerics(QueryHandler.class, query.getClass(), resultType)
        );

        if (registeredQueryHandlers.length == 0) {
            logger.error("Looks like there is no handler registered for {}", query.getClass());
            throw new MissingHandlerException(query);
        }

        if (registeredQueryHandlers.length > 1) {
            logger.error("Looks like on your application there are multiple command handlers for {}", query.getClass());
            throw new MultipleHandlersException(query, registeredQueryHandlers);
        }

        var handlerName = registeredQueryHandlers[0];

        logger.trace("Fine, there is only one handler registered and that is {}", handlerName);

        QueryHandler<Q, R> handler = (QueryHandler<Q, R>) applicationContext.getBean(handlerName);

        return handler.handle(query);
    }


}
