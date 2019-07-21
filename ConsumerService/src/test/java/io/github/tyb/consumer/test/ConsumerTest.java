package io.github.tyb.consumer.test;

import io.github.tyb.consumer.service.Consumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class ConsumerTest {
    @Autowired
    private Consumer consumer;

    @Test
    public void consumeRaw() {
        consumer.consumeRaw();
    }
}
