package io.github.tyb.consumer.test;

import io.github.tyb.consumer.dummy.WeatherService;
import io.github.tyb.consumer.service.Consumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class ProfileTest {

    @Autowired
    Consumer consumer;

    @Autowired
    WeatherService weatherService;

    @Test
    public void testRainingProfile() {
        //String output = consumer.consume();
        String output = weatherService.forecast();
        assertThat(output).contains("Today is sunny day!");
    }
}
