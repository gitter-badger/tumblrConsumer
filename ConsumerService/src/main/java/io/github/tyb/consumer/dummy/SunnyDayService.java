package io.github.tyb.consumer.dummy;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class SunnyDayService implements WeatherService {

    @Override
    public String forecast() {
        return "Today is sunny day!";
    }
}
