package io.github.tyb.consumer.dummy;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
public class RainyDayService implements WeatherService {

    @Override
    public String forecast() {
        return "it is a rainy day!";
    }
}
