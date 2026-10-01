package com.albanoi.spring.autoconfigure;

import com.albanoi.spring.gateway.AlbanoiGateway;
import com.albanoi.spring.gateway.DefaultAlbanoiGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;


@AutoConfiguration
public class AlbanoiAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(AlbanoiGateway.class)
    public AlbanoiGateway albanoiGateway(){
        return new DefaultAlbanoiGateway();
    }
}
