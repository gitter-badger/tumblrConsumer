package io.github.tyb.consumer.domain.types;

import io.github.tyb.consumer.domain.types.post.Post;
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
public class Note {
    @Id
    @GeneratedValue
    private Long id;

    private Long timestamp;
    private String blog_name;
    private String blog_url;
    private String type;

    @ManyToOne
    @JoinColumn
    private Post post;
    //private Long post_id;

    private String reply_text;
    private String added_text;

}
