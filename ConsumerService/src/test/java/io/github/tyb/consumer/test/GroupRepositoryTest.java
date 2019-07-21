package io.github.tyb.consumer.test;

import static org.junit.Assert.assertEquals;

import io.github.tyb.consumer.repository.GroupRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


@DataJpaTest
@RunWith(SpringRunner.class)
public class GroupRepositoryTest {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void check_todo_count() {
        assertEquals(3, groupRepository.count());
    }

}
