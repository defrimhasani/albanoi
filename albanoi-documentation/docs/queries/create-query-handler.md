---
sidebar_position: 2
---

# Create a handler for your query

The query itself contains only data that the handler needs. You can create a handler by implementing the `QueryHandler` interface


```java title="src/core/handlers/GetUserByIdQueryHandler.java"
@Component
public class GetUserByIdQueryHandler implements QueryHandler<GetUserByIdQuery, User> {
    @Override
    public User handle(GetUserByIdQuery query) {
        return new User("user");
    }
}
```