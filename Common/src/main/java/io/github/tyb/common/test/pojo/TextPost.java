package io.github.tyb.common.test.pojo;

import lombok.AllArgsConstructor;
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
