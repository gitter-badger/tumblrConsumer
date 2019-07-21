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
public class Dialogue {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String label;
    private String phrase;
}
