package io.github.tyb.consumer.domain.types.post;

import io.github.tyb.consumer.domain.types.Dialogue;
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
///@Builder
@Entity
public class ChatPost extends Post {

    private String title;
    private String body;

    @OneToMany(mappedBy = "chatPost", cascade = CascadeType.ALL)
    private List<Dialogue> dialogue;

    /*
    public BookCategory(String name, Book... books) {
        this.name = name;
        this.books = Stream.of(books).collect(Collectors.toSet());
        this.books.forEach(x -> x.setBookCategory(this));
    }
     */
}
