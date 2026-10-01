# Albanoi — lightweight CQRS for Spring Boot

[![Java CI](https://github.com/defrimhasani/albanoi/actions/workflows/maven.yml/badge.svg)](https://github.com/defrimhasani/albanoi/actions/workflows/maven.yml)
[![Documentation](https://github.com/defrimhasani/albanoi/actions/workflows/documentation.yml/badge.svg)](https://github.com/defrimhasani/albanoi/actions/workflows/documentation.yml)

Albanoi separates commands (writes) from queries (reads) with small Java interfaces and a Spring Boot gateway that finds your handlers. It is an in-process dispatcher, not a message broker, event store, or persistence framework.

## Versions and requirements

- **Latest published version:** `0.0.2`, available from Maven Central.
- **Development version:** `0.0.3-SNAPSHOT` (build locally; not yet published).
- **Development baseline:** Java 17+, Maven 3.9+, Spring Boot 3.5.16.
- **Documentation tooling:** Node.js 22+; CI uses Node.js 24.

The refresh keeps the existing packages and gateway methods. Commands remain in `com.albanoi`; queries remain in `org.albanoi` for compatibility. See [CHANGELOG.md](CHANGELOG.md) for unreleased changes and [the documentation](https://albanoi.defrimhsn.com) for more examples.

## Install

For the currently published library:

```xml
<dependency>
    <groupId>com.defrimhsn</groupId>
    <artifactId>albanoi-spring-boot-starter</artifactId>
    <version>0.0.2</version>
</dependency>
```

To try the refreshed development version, clone this repository, run `./mvnw clean install`, and use `0.0.3-SNAPSHOT` instead. The starter includes both core modules and auto-configures an `AlbanoiGateway`. In the development version, a gateway bean you provide replaces the default.

## Commands

```java
import com.albanoi.Command;
import com.albanoi.CommandHandler;
import com.albanoi.CommandResult;
import org.springframework.stereotype.Component;

public record CreateUserCommand(String username) implements Command {}

@Component
public class CreateUserCommandHandler implements CommandHandler<CreateUserCommand, User> {
    @Override
    public CommandResult<User> execute(CreateUserCommand command) {
        return CommandResult.of(new User(command.username()));
    }
}
```

Put each public type in its own file. For commands without a return value, use `CommandHandler<MyCommand, Void>` and return `CommandResult.noResult()`.

## Queries

```java
import org.albanoi.Query;
import org.albanoi.QueryHandler;
import org.springframework.stereotype.Component;
import java.util.UUID;

public record GetUserByIdQuery(UUID id) implements Query {}

@Component
public class GetUserByIdQueryHandler implements QueryHandler<GetUserByIdQuery, User> {
    @Override
    public User handle(GetUserByIdQuery query) {
        // Look up the user in your repository here.
        return new User("user");
    }
}
```

## Dispatch from a controller

```java
@RestController
@RequestMapping("/users")
public class UsersController {
    private final AlbanoiGateway gateway;

    public UsersController(AlbanoiGateway gateway) {
        this.gateway = gateway;
    }

    @PostMapping
    public User create(@RequestBody CreateUserRequest request) {
        return gateway.execute(new CreateUserCommand(request.username()), User.class).getResult();
    }

    @GetMapping("/{id}")
    public User find(@PathVariable UUID id) {
        return gateway.handle(new GetUserByIdQuery(id), User.class);
    }
}
```

The examples assume your own `User` and `CreateUserRequest` types. Register handlers as Spring beans in your application's component scan. Exactly one handler must match the **message class and result class**. Missing handlers throw `MissingHandlerException`; duplicates throw `MultipleHandlersException`. Handlers execute synchronously, and their exceptions propagate to the caller.

## Build and test

```sh
./mvnw clean verify
```

The root Maven reactor builds both core libraries, the starter, and the sample together. CI runs on Java 17, 21, and 25.

Run the packaged sample after building:

```sh
java -jar samples/spring-boot-sample/target/spring-boot-sample-0.0.3-SNAPSHOT.jar
```

Build the documentation:

```sh
cd albanoi-documentation
npm ci
npm run typecheck
npm run build
```

See [RELEASING.md](RELEASING.md) for Maven Central publication. Ordinary builds and CI do not publish library artifacts.

## License

[Apache License 2.0](LICENSE).
