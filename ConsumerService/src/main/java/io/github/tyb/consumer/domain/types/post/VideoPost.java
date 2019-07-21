package io.github.tyb.consumer.domain.types.post;

import io.github.tyb.consumer.domain.types.Video;
import io.github.tyb.consumer.domain.types.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.File;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Entity
public class VideoPost extends Post {

    @OneToMany(mappedBy = "videoPost", cascade = CascadeType.ALL)
    private List<Video> player;

    private String caption;
    private String embed, permalink_url;
    private File data;
    private String thumbnail_url;
    private int thumbnail_width;
    private int thumbnail_height;
}
