package io.github.tyb.consumer.domain.types.post;

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
public class LinkPost extends Post {

    private String title;
    private String url;
    private String description;

}
