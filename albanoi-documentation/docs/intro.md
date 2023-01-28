---
sidebar_position: 1
---

# Introduction

Albanoi is an open-source library designed to assist Spring developers in implementing the Command-Query Responsibility Segregation (CQRS) pattern in their applications. The CQRS pattern is an architectural pattern that separates the responsibilities of handling command and query operations, allowing for more flexibility and scalability in an application's architecture.

The library provides a set of annotations and utility classes that help to simplify the implementation of CQRS in Spring applications. It allows developers to easily define command and query handlers.

## Getting started

If you're using spring boot, Albanoi includes a
spring boot starter that can only be used by including
the library on the classpath.

```xml
<dependency>
    <groupId>com.defrimhsn</groupId>
    <artifactId>albanoi-spring-boot-starter</artifactId>
</dependency>
```

Once the library is in your classpath, albanoi will register its `AlbanoiGateway` using Spring Boot's autoconfiguration.