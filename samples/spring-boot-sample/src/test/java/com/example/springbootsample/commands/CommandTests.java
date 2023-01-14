package com.example.springbootsample.commands;

import com.albanoi.CommandResult;
import com.albanoi.spring.gateway.AlbanoiGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class CommandTests {

    @Autowired
    private AlbanoiGateway albanoiGateway;
    @Test
    @DisplayName("Execute a command successfully")
    void executeCommandSuccessfully(){

        CreateUserCommand command = new CreateUserCommand("US1", "12345");

        CommandResult<UUID> execute = albanoiGateway.execute(command);

        System.out.println(execute.getResult());
    }
}
