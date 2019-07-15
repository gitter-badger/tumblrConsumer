package io.github.tyb;

import io.github.tyb.repository.GroupRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@EnableAutoConfiguration
public class TumblrConsumerRestApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(TumblrConsumerRestApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(GroupRepository repository) {
        //return r -> log.info(repository.findAll().toString());
        return null;
    }
}

