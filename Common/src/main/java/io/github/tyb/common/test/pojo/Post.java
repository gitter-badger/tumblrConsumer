package io.github.tyb.common.test.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@EqualsAndHashCode(exclude = {"tags", "notes"})
public class Post {
    private Long id;

    public enum PostType {
        TEXT("text"),
        PHOTO("photo"),
        QUOTE("quote"),
        LINK("link"),
        CHAT("chat"),
        AUDIO("audio"),
        VIDEO("video"),
        ANSWER("answer"),
        POSTCARD("postcard"),
        UNKNOWN("unknown");

        private final String mType;

        PostType(final String type) {
            this.mType = type;
        }

        public String getValue() {
            return this.mType;
        }
    }

    protected PostType type;

    //Author - kişilerle entegre olarak kullanılacaka.
    private String author;

    //Buna ihtiyaç yok.
    private String reblog_key;

    //Buna ihtiyaç yok.
    private String blog_name;

    //Buna ihtiyaç yok.
    private String post_url, short_url;

    private Long timestamp;

    //Buna ihtiyaç yok.
    private Long liked_timestamp;

    private Content content;

    //ÖNEMLİ: Indicates the current state of the post: States are published, queued, draft and private
    private String state;

    //The post format: html or markdown
    private String format;

    private String date;

    private List<Tag> tags;

    private Boolean bookmarklet, mobile;

    //the URL for the source of the content (for quotes, reblogs, etc.)
    private String source_url;

    //The title of the source site
    private String source_title;
    private Boolean liked;
    private String slug;
    private Long reblogged_from_id, reblogged_root_id;
    private String reblogged_from_url, reblogged_from_name, reblogged_from_title;
    private String reblogged_root_url, reblogged_root_name, reblogged_root_title;
    private Long note_count;

    //ÖNEMLİ!
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Note> notes;



}
