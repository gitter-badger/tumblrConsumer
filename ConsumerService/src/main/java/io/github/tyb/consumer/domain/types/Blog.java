package io.github.tyb.consumer.domain.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Entity
public class Blog {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String title;
    private String description;
    private int posts, likes, followers;
    private Long updated;
    private boolean ask, ask_anon;

}
