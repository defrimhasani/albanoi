---
sidebar_position: 1
---

# Create a command

In the Command-Query Responsibility Segregation (CQRS) pattern, commands represent the operations that change the state of the system. They are typically used to create, update, or delete data within an application. A command is often represented by a message or a request that contains the data needed to execute the operation.

You can create a command by implementing the `Command` interface.

```java title="src/core/commands/CreateUserCommand.java"
public class CreateUserCommand implements Command {
    public String username;
}
```
