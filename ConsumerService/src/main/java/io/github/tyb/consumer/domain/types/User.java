package io.github.tyb.consumer.domain.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Entity
public class User{
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "blog_id", nullable = false)
    private List<Blog> blogs;

    private String name;

    //private Object following;
    private String following;
    private Integer likes;
    private String default_post_format;

}
