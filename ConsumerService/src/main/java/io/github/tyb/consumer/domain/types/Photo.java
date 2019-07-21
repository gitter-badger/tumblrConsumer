package io.github.tyb.consumer.domain.types;

import io.github.tyb.consumer.domain.types.post.PhotoPost;
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
public class Photo {
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Types of the post - what kind of data does it have?
     */
    public enum PhotoType {
        SOURCE("source"),
        FILE("data");

        private final String prefix;
        private PhotoType(String prefix) {
            this.prefix = prefix;
        }

        public String getPrefix() {
            return this.prefix;
        }
    };

    private String caption;

    @OneToMany(mappedBy = "photo", cascade = CascadeType.ALL)
    private List<PhotoSize> alt_sizes;

    @OneToOne
    private PhotoSize original_size;

    //mappedBy reference an unknown target entity property: io.github.tyb.consumer.domain.types.Photo.photoPost in io.github.tyb.consumer.domain.types.post.PhotoPost.photos
    //bu hatayı geçmek için ekledim.
    @ManyToOne
    @JoinColumn
    private PhotoPost photoPost;

    private String source;
    private File file;


}
