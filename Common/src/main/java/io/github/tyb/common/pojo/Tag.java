package io.github.tyb.common.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Tag {

    private Long id;

    @NonNull
    private String name;

    private String description;

    //Returns an image (75x75) associated with the tag; may be null
    private String thumb_url;
}
