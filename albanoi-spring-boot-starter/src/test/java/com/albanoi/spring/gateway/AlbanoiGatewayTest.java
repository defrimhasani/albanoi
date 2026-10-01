package com.albanoi.spring.gateway;

import com.albanoi.Command;
import com.albanoi.CommandHandler;
import com.albanoi.CommandResult;
import com.albanoi.spring.autoconfigure.AlbanoiAutoConfiguration;
import com.albanoi.spring.gateway.exceptions.MissingHandlerException;
import com.albanoi.spring.gateway.exceptions.MultipleHandlersException;
import org.albanoi.Query;
import org.albanoi.QueryHandler;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AlbanoiGatewayTest {
    private final ApplicationContextRunner runner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(AlbanoiAutoConfiguration.class));

    @Test
    void autoConfigurationIsDiscoveredFromTheClasspath() {
        new ApplicationContextRunner().withUserConfiguration(DiscoveryConfiguration.class)
                .run(context -> assertThat(context).hasSingleBean(AlbanoiGateway.class));
    }

    @Test
    void defaultGatewayIsRegistered() {
        runner.run(context -> {
            assertThat(context).hasSingleBean(AlbanoiGateway.class);
            assertThat(context.getBean(AlbanoiGateway.class)).isInstanceOf(DefaultAlbanoiGateway.class);
        });
    }

    @Test
    void customGatewayReplacesTheDefault() {
        runner.withUserConfiguration(CustomGatewayConfiguration.class).run(context -> {
            assertThat(context).hasSingleBean(AlbanoiGateway.class);
            assertThat(context.getBean(AlbanoiGateway.class)).isSameAs(context.getBean("customGateway"));
        });
    }

    @Test
    void dispatchesCommandsAndQueries() {
        runner.withUserConfiguration(Handlers.class).run(context -> {
            var gateway = context.getBean(AlbanoiGateway.class);
            assertThat(gateway.execute(new GreetingCommand("Ada"), String.class).getResult()).isEqualTo("Ada");
            assertThat(gateway.handle(new GreetingQuery("Grace"), String.class)).isEqualTo("Grace");
        });
    }

    @Test
    void missingCommandHandlerIsReported() {
        runner.run(context -> assertThatThrownBy(() -> context.getBean(AlbanoiGateway.class)
                .execute(new GreetingCommand("Ada"), String.class)).isInstanceOf(MissingHandlerException.class));
    }

    @Test
    void missingQueryHandlerIsReported() {
        runner.run(context -> assertThatThrownBy(() -> context.getBean(AlbanoiGateway.class)
                .handle(new GreetingQuery("Ada"), String.class)).isInstanceOf(MissingHandlerException.class));
    }

    @Test
    void resultTypeIsPartOfHandlerSelection() {
        runner.withUserConfiguration(Handlers.class).run(context -> {
            var gateway = context.getBean(AlbanoiGateway.class);
            assertThatThrownBy(() -> gateway.execute(new GreetingCommand("Ada"), Integer.class))
                    .isInstanceOf(MissingHandlerException.class);
            assertThatThrownBy(() -> gateway.handle(new GreetingQuery("Ada"), Integer.class))
                    .isInstanceOf(MissingHandlerException.class);
        });
    }

    @Test
    void duplicateCommandHandlersAreReported() {
        runner.withUserConfiguration(Handlers.class, DuplicateCommandHandler.class).run(context ->
                assertThatThrownBy(() -> context.getBean(AlbanoiGateway.class)
                        .execute(new GreetingCommand("Ada"), String.class))
                        .isInstanceOf(MultipleHandlersException.class));
    }

    @Test
    void duplicateQueryHandlersAreReported() {
        runner.withUserConfiguration(Handlers.class, DuplicateQueryHandler.class).run(context ->
                assertThatThrownBy(() -> context.getBean(AlbanoiGateway.class)
                        .handle(new GreetingQuery("Ada"), String.class))
                        .isInstanceOf(MultipleHandlersException.class));
    }

    record GreetingCommand(String name) implements Command {}
    record GreetingQuery(String name) implements Query {}

    @Configuration(proxyBeanMethods = false)
    @EnableAutoConfiguration
    static class DiscoveryConfiguration {}

    @Configuration(proxyBeanMethods = false)
    static class Handlers {
        @Bean
        CommandHandler<GreetingCommand, String> commandHandler() {
            return command -> CommandResult.of(command.name());
        }

        @Bean
        QueryHandler<GreetingQuery, String> queryHandler() {
            return GreetingQuery::name;
        }
    }

    @Configuration(proxyBeanMethods = false)
    static class DuplicateCommandHandler {
        @Bean
        CommandHandler<GreetingCommand, String> secondCommandHandler() {
            return command -> CommandResult.of(command.name());
        }
    }

    @Configuration(proxyBeanMethods = false)
    static class DuplicateQueryHandler {
        @Bean
        QueryHandler<GreetingQuery, String> secondQueryHandler() {
            return GreetingQuery::name;
        }
    }

    @Configuration(proxyBeanMethods = false)
    static class CustomGatewayConfiguration {
        @Bean
        AlbanoiGateway customGateway() {
            return new DefaultAlbanoiGateway();
        }
    }
}
