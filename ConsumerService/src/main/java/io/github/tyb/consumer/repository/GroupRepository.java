package io.github.tyb.consumer.repository;

import io.github.tyb.consumer.domain.tutorial.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findByName(String name);
}
