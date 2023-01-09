package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueryHandlerTest {
    static class ReadUserQuery implements Query{
        public String username;
    }

    static class User{
        public String username;

        public User(String username) {
            this.username = username;
        }
    }

    static class ReadUserQueryHandler implements
            QueryHandler<ReadUserQuery, User>{

        @Override
        public User handle(ReadUserQuery query) {
            return new User(query.username);
        }
    }

    @Test
    @DisplayName("Read a user")
    void readUser(){
        var readUserQuery = new ReadUserQuery();
        readUserQuery.username = "ADM";
        var readUserQueryHandler = new ReadUserQueryHandler();
        User handle = readUserQueryHandler.handle(readUserQuery);
        assertNotNull(handle);
    }
}