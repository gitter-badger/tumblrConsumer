package io.github.tyb.consumer.domain.types.post;

import com.tumblr.jumblr.types.Photo.PhotoType;
import io.github.tyb.consumer.domain.types.Photo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Entity
public class PhotoPost extends Post {

    private String caption;
    private Integer width, height;

    private String link;

    @OneToMany(mappedBy = "photoPost", cascade = CascadeType.ALL)
    private List<Photo> photos;

    protected List<Photo> pendingPhotos;
    protected PhotoType postType = null;


}
