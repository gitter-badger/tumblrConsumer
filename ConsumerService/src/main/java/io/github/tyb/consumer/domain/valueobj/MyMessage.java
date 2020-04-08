package io.github.tyb.consumer.domain.valueobj;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MyMessage {
    private String text;
}
