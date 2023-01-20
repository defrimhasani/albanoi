package com.example.springbootsample.commands;

import com.albanoi.CommandResult;
import com.albanoi.spring.gateway.AlbanoiGateway;
import com.albanoi.spring.gateway.exceptions.MultipleHandlersException;
import com.example.springbootsample.models.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommandTests {

    @Autowired
    private AlbanoiGateway albanoiGateway;

    @Test
    @DisplayName("Execute a command successfully")
    void executeCommandSuccessfully() {

        var createUserCommand = new CreateUserCommand();
        createUserCommand.setUsername("US");

        CommandResult<User> executionResult = albanoiGateway.execute(createUserCommand, User.class);

        assertTrue(executionResult.hasResult());
        assertInstanceOf(User.class, executionResult.getResult());
    }

    @Test
    @DisplayName("Attempt execution when multiple handers are registered")
    void whenMultipleHandlersRegistered() {

        var deleteUserCommand = new DeleteUserCommand();

        assertThrows(MultipleHandlersException.class,
                () -> albanoiGateway.execute(deleteUserCommand, String.class));
    }

}
