package io.github.tyb.consumer.domain.types.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Entity
//@PrimaryKeyJoinColumn(name = "postId")
public class TextPost extends Post {
    private String title;

    @Lob
    @Column
    private String body;
}
