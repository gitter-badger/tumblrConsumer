package io.github.tyb.consumer.repository;

import io.github.tyb.consumer.domain.types.post.Post;
import org.springframework.stereotype.Repository;


@Repository
//public interface PostRepository extends JpaRepository<Post, Integer> {
public interface PostRepository extends PostBaseRepository<Post> {
}
