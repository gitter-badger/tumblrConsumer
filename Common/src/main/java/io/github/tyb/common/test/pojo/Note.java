package io.github.tyb.common.test.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Entity
public class Note {
    private Long id;

    private Long timestamp;
    private String blog_name;
    private String blog_url;
    private String type;
    private String reply_text;
    private String added_text;

}
