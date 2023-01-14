package com.example.springbootsample.commands;

import com.albanoi.CommandHandler;
import com.albanoi.CommandResult;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateUserCommandHandler implements CommandHandler<CreateUserCommand, UUID> {
    @Override
    public CommandResult<UUID> execute(CreateUserCommand createUserCommand) {
        return new CommandResult<>(UUID.randomUUID());
    }
}
