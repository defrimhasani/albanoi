package com.example.springbootsample.commands;

import com.albanoi.Command;

public class CreateUserCommand implements Command {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CreateUserCommand(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
