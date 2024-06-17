package com.sivalabs.myapp.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest
//@DataJpaTest
@DataJpaTest(// testcontainers (using real time prod db - postgresql(pre req: docker desktop keep running), in testing if h2 is available in pom, then it picks it without testcontainers)
        properties = {
                "spring.test.database.replace=none",
                "spring.datasource.url=jdbc:tc:postgresql:16-alpine:///testdb"
        }
)
class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    void shouldGetPersonByIdReturnEmptyWhenNotFound() {
        var person = personRepository.findById(1L);
        assertThat(person).isNotEmpty();
    }
}