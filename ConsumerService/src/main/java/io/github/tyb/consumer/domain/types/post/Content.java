package io.github.tyb.consumer.domain.types.post;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Entity
public class Content {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "content"
    )
    private Post post;
}
