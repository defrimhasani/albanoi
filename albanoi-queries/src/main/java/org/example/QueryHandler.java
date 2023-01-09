package org.example;

public interface QueryHandler<Q extends Query, R> {
    R handle(Q query);
}
