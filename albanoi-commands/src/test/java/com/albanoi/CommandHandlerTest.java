package com.albanoi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandHandlerTest {

    protected static class CreateUserCommand implements Command{
        public String username;
    }

    protected static class User {
        public String username;

        public User(String username){
            this.username = username;
        }
    }

    protected static class CreateUserCommandHandler implements CommandHandler<CreateUserCommand, User> {

        @Override
        public CommandResult<User> execute(CreateUserCommand command) {
            return CommandResult.of(new User(command.username));
        }
    }

    @Test
    void createUserUsingHandler(){
        CreateUserCommand command = new CreateUserCommand();
        command.username = "User";
        CreateUserCommandHandler handler = new CreateUserCommandHandler();
        CommandResult<User> executionResult = handler.execute(command);
        assertTrue(executionResult.hasResult());
        assertInstanceOf(CommandResult.class, executionResult);
        assertInstanceOf(User.class, executionResult.getResult());
        assertEquals(command.username, executionResult.getResult().username);
    }

}