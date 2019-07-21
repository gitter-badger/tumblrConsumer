package io.github.tyb.consumer.domain.types.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Entity
public class AnswerPost extends Post {

    private String asking_name, asking_url;
    private String question;
    private String answer;

}
