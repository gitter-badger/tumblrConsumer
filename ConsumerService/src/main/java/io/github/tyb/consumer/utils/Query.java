package io.github.tyb.consumer.utils;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class Query {
    public static Map<String, Integer> requestOptions = new HashMap<String, Integer>();
    static {
        String startTs = "1999-11-27T16:30:00.21234Z";
        String stopTs = "2099-11-27T16:30:00.21234Z";
        requestOptions.put("limit", 20);
        requestOptions.put("offset", 0);
        requestOptions.put("before", (int) Instant.parse(startTs).toEpochMilli()); //stop timestamp
        requestOptions.put("after", (int) Instant.parse(stopTs).toEpochMilli()); //start timestamp
    }
}
