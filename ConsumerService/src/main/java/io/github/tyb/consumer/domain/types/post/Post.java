package io.github.tyb.consumer.domain.types.post;

import io.github.tyb.consumer.domain.Tag;
import io.github.tyb.consumer.domain.types.Note;
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
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Post {
    @Id
    @GeneratedValue
    private Long id;

    //TODO: to map an Enum using JPA and Hibernate.
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

    //Post type: VARCHAR(8) oluşturulacak.
    // Now, when inserting a Post entity:: post.setStatus( PostStatus.PENDING );
    @Enumerated(EnumType.STRING)
    @Column(length = 8)
    //TODO: bir sonraki iterasyonda ayrı tablo olarak yapılacak.
    //https://vladmihalcea.com/the-best-way-to-map-an-enum-type-with-jpa-and-hibernate/
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

    /*
    @OneToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(name = "content_id", nullable = false)
    private Content content;
    */

    //ÖNEMLİ: Indicates the current state of the post: States are published, queued, draft and private
    private String state;

    //The post format: html or markdown
    private String format;

    private String date;

    //ÖNEMLİ: Tags applied to the post
    //private List<String> tags;
    //TODO: create tag pojo
    //TODO: manage relationship
    // one post has tags and a tag has many posts. TAG N-N, category olsaydı 1-n olurdu.
    @ManyToMany(cascade = CascadeType.ALL)
    // Thanks to CascadeType.ALL, associated entity Tag will be saved at the same time with Book
    // without the need of calling its save function explicitly.
    @JoinTable(name = "PostTag",
            joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
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
