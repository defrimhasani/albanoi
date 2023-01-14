package com.albanoi.spring.gateway;

import com.albanoi.Command;
import com.albanoi.CommandHandler;
import com.albanoi.CommandResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;

public class DefaultAlbanoiGateway implements AlbanoiGateway {

    @Autowired
    private ApplicationContext applicationContext;
    @Override
    public <R, C extends Command> CommandResult<R> execute(C command) {

        String[] beanNamesForType = applicationContext.getBeanNamesForType(
                ResolvableType.forClassWithGenerics(CommandHandler.class,
                        command.getClass(), CommandResult.class
                )
        );

        for (String s : beanNamesForType) {
            System.out.println("#### " + s);
        }

        return null;
    }
}
