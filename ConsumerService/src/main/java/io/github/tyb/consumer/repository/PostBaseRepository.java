package io.github.tyb.consumer.repository;

import io.github.tyb.consumer.domain.types.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostBaseRepository<T extends Post> extends JpaRepository<T, Integer> {
}
