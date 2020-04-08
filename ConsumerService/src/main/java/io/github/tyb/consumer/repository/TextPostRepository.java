package io.github.tyb.consumer.repository;

import io.github.tyb.consumer.domain.types.post.TextPost;
import org.springframework.stereotype.Repository;

@Repository
public interface TextPostRepository extends PostBaseRepository<TextPost> {
}
