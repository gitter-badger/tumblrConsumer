package io.github.tyb.consumer.domain.types.post;

import io.github.tyb.consumer.domain.types.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Entity
public class QuotePost extends Post {

    private String text;
    private String source;


}
