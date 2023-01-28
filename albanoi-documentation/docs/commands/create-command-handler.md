---
sidebar_position: 2
---

# Create a handler for your command

The command itself contains only information that the handler needs to do something with it.

You can create a handler by implementing the `CommandHandler` interface, which looks like this

```java title="src/core/handlers/CreateUserCommandHandler.java"
@Component
class CreateUserCommandHandler implements CommandHandler<CreateUserCommand, User> {

    @Override
    public CommandResult<User> execute(CreateUserCommand command) {
        return CommandResult.of(new User(command.username));
    }
}
```

The `CommandHandler` interface has two generic parameters, 
where the first one is the command class itself, and the second is the command result 
which will be returned inside the `CommandResult` class.
