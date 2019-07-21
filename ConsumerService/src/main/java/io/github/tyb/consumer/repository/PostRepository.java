package io.github.tyb.consumer.repository;

import io.github.tyb.consumer.domain.types.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Integer> {
}
