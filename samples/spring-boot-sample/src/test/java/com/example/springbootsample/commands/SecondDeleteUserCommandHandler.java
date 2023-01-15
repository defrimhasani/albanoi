package com.example.springbootsample.commands;

import com.albanoi.CommandHandler;
import com.albanoi.CommandResult;
import org.springframework.stereotype.Component;

@Component
public class SecondDeleteUserCommandHandler implements CommandHandler<DeleteUserCommand, String> {
    @Override
    public CommandResult<String> execute(DeleteUserCommand command) {
        return CommandResult.of("2");
    }
}
