package com.albanoi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CommandTest {
    static class CreateUserCommand implements Command {
        private final String username;
        public CreateUserCommand(String username){
            this.username = username;
        }
    }

    @Test
    void verifyCommandCreation(){
        var createUserCommand = new CreateUserCommand("US");
        Assertions.assertInstanceOf(Command.class, createUserCommand);
    }




}