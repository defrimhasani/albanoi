package com.example.springbootsample.commands;

import com.albanoi.Command;

public class CreateUserCommand implements Command {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
