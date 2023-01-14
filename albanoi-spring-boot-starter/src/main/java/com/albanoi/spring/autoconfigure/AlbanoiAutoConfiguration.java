package com.albanoi.spring.autoconfigure;

import com.albanoi.spring.gateway.AlbanoiGateway;
import com.albanoi.spring.gateway.DefaultAlbanoiGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AlbanoiAutoConfiguration {

    @Bean
    public AlbanoiGateway albanoiGateway(){
        return new DefaultAlbanoiGateway();
    }
}
