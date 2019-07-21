package io.github.tyb.consumer.domain.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Entity
public class Video {
    @Id
    @GeneratedValue
    private Long id;

    private Integer width;
    private String embed_code;
}
