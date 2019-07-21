package io.github.tyb.consumer.domain.types.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.io.File;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Entity
public class AudioPost extends Post {

    private String caption, player, audio_url;
    private Integer plays;
    private String album_art, artist, album, track_name;
    private Integer track_number, year;

    private File data;
    private String external_url;


}