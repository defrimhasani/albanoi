package com.example.springbootsample.controllers;

import com.albanoi.CommandResult;
import com.albanoi.spring.gateway.AlbanoiGateway;
import com.example.springbootsample.commands.CreateUserCommand;
import com.example.springbootsample.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final AlbanoiGateway albanoiGateway;

    public UsersController(AlbanoiGateway albanoiGateway) {
        this.albanoiGateway = albanoiGateway;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest createUserRequest){

        var createUserCommand = new CreateUserCommand();
        createUserCommand.setUsername(createUserRequest.getUsername());

        CommandResult<User> createUserResult = albanoiGateway.execute(createUserCommand, User.class);

        return ResponseEntity.ok(createUserResult.getResult());

    }
}
