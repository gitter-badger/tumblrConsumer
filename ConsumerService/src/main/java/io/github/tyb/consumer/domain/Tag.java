package io.github.tyb.consumer.domain;

import io.github.tyb.consumer.domain.types.post.Post;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Tag {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    private String description;

    //Returns an image (75x75) associated with the tag; may be null
    private String thumb_url;

    @ManyToMany(mappedBy = "tags")
    private List<Post> posts;

    /*
    @ManyToOne(cascade=CascadeType.PERSIST)
    private UserOld user;

    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<Event> events;
    */
}
