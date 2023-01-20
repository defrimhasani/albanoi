package com.example.springbootsample.queries;

import com.example.springbootsample.models.User;
import org.albanoi.QueryHandler;
import org.springframework.stereotype.Component;


@Component
public class GetUserByIdQueryHandler implements QueryHandler<GetUserByIdQuery, User> {
    @Override
    public User handle(GetUserByIdQuery query) {
        return new User("user");
    }
}
