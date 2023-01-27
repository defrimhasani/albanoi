package com.example.springbootsample.controllers;

import com.albanoi.CommandResult;
import com.albanoi.spring.gateway.AlbanoiGateway;
import com.example.springbootsample.commands.CreateUserCommand;
import com.example.springbootsample.models.User;
import com.example.springbootsample.queries.GetUserByIdQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
    @GetMapping("{id}")
    public ResponseEntity<User> findUser(@PathVariable UUID id){

        GetUserByIdQuery getUserByIdQuery = new GetUserByIdQuery(id);
        User user = albanoiGateway.handle(getUserByIdQuery, User.class);

        return ResponseEntity.ok(user);

    }
}
