package io.github.tyb.consumer.domain.types;

import io.github.tyb.consumer.domain.types.post.VideoPost;
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
public class Video {
    @Id
    @GeneratedValue
    private Long id;

    private Integer width;
    private String embed_code;

    @ManyToOne
    @JoinColumn
    private VideoPost videoPost;
}
