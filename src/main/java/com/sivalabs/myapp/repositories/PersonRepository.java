package com.sivalabs.myapp.repositories;

import com.sivalabs.myapp.entities.Person;
import com.sivalabs.myapp.models.PersonBasicView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
//    Optional<Person> findByEmailAndPasswordAndActiveIsTrue(String email, String password);

    @Query("SELECT p FROM Person p WHERE p.email = :email AND p.password = :password AND p.active = true")
    Optional<Person> login(String email, String password);

    Page<PersonBasicView> findAllBy(Pageable pageable);

    @Query("SELECT p FROM Person p LEFT JOIN FETCH p.phones")
    Page<Person> findAllEntitiesBy(Pageable pageable);
}
