package com.example.springbootsample.queries;

import org.albanoi.Query;

import java.util.UUID;


public record GetUserByIdQuery(UUID id) implements Query {

}
