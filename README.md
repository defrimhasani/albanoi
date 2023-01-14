## Simplifying CQRS with Albanoi

### Overview

Albanoi is an open-source library designed to assist Spring developers in implementing the Command-Query Responsibility Segregation (CQRS) pattern in their applications. The CQRS pattern is an architectural pattern that separates the responsibilities of handling command and query operations, allowing for more flexibility and scalability in an application's architecture.

The library provides a set of annotations and utility classes that help to simplify the implementation of CQRS in Spring applications.
It allows developers to easily define command and query handlers.


### Commands

In the Command-Query Responsibility Segregation (CQRS) pattern, commands represent the operations that change the state of the system. They are typically used to create, update, or delete data within an application. A command is often represented by a message or a request that contains the data needed to execute the operation.

You can create a command by implementing the `Command` interface.
``` java
class CreateUserCommand implements Command {
        public String username;
}
```
The command itself contains only information that the handler needs to do something with it.

You can create a handler by implementing the `CommandHandler` interface, which looks like this
```java
class CreateUserCommandHandler implements CommandHandler<CreateUserCommand, User> {

        @Override
        public CommandResult<User> execute(CreateUserCommand command) {
            return CommandResult.of(new User(command.username));
        }
    }
```
The `CommandHandler` interface has two generic parameters, where the first one is the command class itself, and the second is the command result which will be returned inside the `CommandResult` class.

### Queries

In the Command-Query Responsibility Segregation (CQRS) pattern, queries represent the operations that retrieve data from the system. They are typically used to read or retrieve data within an application, and are typically represented by a message or a request that contains the data needed to execute the operation.









