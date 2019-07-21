package io.github.tyb.writer;

import io.github.tyb.common.config.WebSocketConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Slf4j
@EnableAutoConfiguration
@ComponentScan(basePackages = {"io.github.tyb.consumer.*", "io.github.tyb.common"})
@Import({WebSocketConfig.class})
public class WriterServiceMain {
    public static void main(String[] args) {
        SpringApplication.run(WriterServiceMain.class, args);
    }
}
