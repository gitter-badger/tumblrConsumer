package io.github.tyb.consumer.domain.types.post;

import io.github.tyb.consumer.domain.types.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Entity
@PrimaryKeyJoinColumn(name = "postId")
public class TextPost extends Post {

    private String title;
    private String body;


}
