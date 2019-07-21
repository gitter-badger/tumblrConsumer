package io.github.tyb.consumer.domain.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Entity
public class PhotoSize {
    @Id
    @GeneratedValue
    private Long id;

    private int width, height;
    private String url;

    @ManyToOne
    @JoinColumn
    private Photo photo;

}
