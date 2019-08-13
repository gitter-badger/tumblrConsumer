package io.github.tyb.consumer;

import io.github.tyb.common.test.config.WebSocketConfig;
import io.github.tyb.consumer.repository.GroupRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@Slf4j
@ComponentScan(basePackages = {"io.github.tyb.consumer.*", "io.github.tyb.common"})
@Import({WebSocketConfig.class/*, SecurityConfig.class*/})
public class TumblrConsumerRestApiApplication {
    public static void main(String[] args) {
        // or java -jar myapp.jar --spring.profiles.active=dev

        //SpringApplication.run(TumblrConsumerRestApiApplication.class, args);
        new SpringApplicationBuilder()
                .sources(TumblrConsumerRestApiApplication.class)
                .profiles("dev")
                .run(args);
    }

    @Bean
    public CommandLineRunner runner(GroupRepository repository) {
        //return r -> log.info(repository.findAll().toString());
        return null;
    }
}

