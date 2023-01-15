package com.example.springbootsample.commands;

import com.albanoi.CommandHandler;
import com.albanoi.CommandResult;
import com.example.springbootsample.models.User;
import org.springframework.stereotype.Component;

@Component
public class CreateUserCommandHandler implements CommandHandler<CreateUserCommand, User> {
    @Override
    public CommandResult<User> execute(CreateUserCommand command) {
        return CommandResult.of(new User(command.getUsername().toUpperCase()));
    }
}
