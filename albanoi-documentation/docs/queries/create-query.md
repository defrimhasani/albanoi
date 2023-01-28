---
sidebar_position: 1
---

# Create a query

In the Command-Query Responsibility Segregation (CQRS) pattern, queries represent the operations that retrieve data from the system. They are typically used to read or retrieve data within an application, and are typically represented by a message or a request that contains the data needed to execute the operation.

You can create a query by implementing the `Query` interface.

```java title="src/core/queries/GetUserByIdQuery.class"
public record GetUserByIdQuery(UUID id) implements Query { }
```

