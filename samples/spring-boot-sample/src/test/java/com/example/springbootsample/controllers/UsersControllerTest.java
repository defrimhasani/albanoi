package com.example.springbootsample.controllers;

import com.example.springbootsample.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UsersControllerTest {
    @Autowired
    private TestRestTemplate rest;

    @Test
    void createsAUserThroughTheCommandGateway() {
        var response = rest.postForEntity("/users", Map.of("username", "Ada"), User.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getUsername()).isEqualTo("ADA");
    }

    @Test
    void findsAUserThroughTheQueryGateway() {
        var response = rest.getForEntity("/users/{id}", User.class, UUID.randomUUID());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getUsername()).isEqualTo("user");
    }

    @Test
    void rejectsAnInvalidUserId() {
        var response = rest.getForEntity("/users/not-a-uuid", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
