package io.github.tyb.consumer;

import io.github.tyb.common.test.config.WebSocketConfig;
import io.github.tyb.consumer.repository.GroupRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@Slf4j
//@ComponentScan(basePackages = {"io.github.tyb.consumer.*", "io.github.tyb.common.*", "com.tumblr.jumblr.types.*"})
@ComponentScan(basePackages = {"io.github.tyb.consumer.*", "io.github.tyb.common.*"})
@Import({WebSocketConfig.class/*, SecurityConfig.class*/})
//@EnableJpaRepositories("io.github.tyb.consumer.repository.*")
//@EntityScan(basePackages = {"com.tumblr.jumblr.types.*"})
public class TumblrConsumerRestApiApplication {
    public static void main(String[] args) {
        // or java -jar myapp.jar --spring.profiles.active=dev

        SpringApplication.run(TumblrConsumerRestApiApplication.class, args);
        /*
        new SpringApplicationBuilder()
                .sources(TumblrConsumerRestApiApplication.class)
                .profiles("dev")
                .run(args);
                */
    }

    @Bean
    public CommandLineRunner runner(GroupRepository repository) {
        //return r -> log.info(repository.findAll().toString());
        return null;
    }

    // Fix the CORS errors
    @Bean
    public FilterRegistrationBean simpleCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // *** URL below needs to match the Vue client URL and port ***
        config.setAllowedOrigins(Collections.singletonList("http://localhost:8000"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}

