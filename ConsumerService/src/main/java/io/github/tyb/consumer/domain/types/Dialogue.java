package io.github.tyb.consumer.domain.types;

import io.github.tyb.consumer.domain.types.post.ChatPost;
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
public class Dialogue {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String label;
    private String phrase;

    @ManyToOne
    @JoinColumn
    private ChatPost chatPost;
}
