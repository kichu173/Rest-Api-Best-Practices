package com.sivalabs.myapp.services;

import com.sivalabs.myapp.entities.Person;
import com.sivalabs.myapp.models.CreatePersonRequest;
import com.sivalabs.myapp.models.PagedResult;
import com.sivalabs.myapp.models.PersonBasicView;
import com.sivalabs.myapp.models.UpdatePersonRequest;
import com.sivalabs.myapp.repositories.PersonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PagedResult<PersonBasicView> getAllPersons(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 10, Sort.by("id").ascending());// 10 records per page
//        Page<Person> personPage = personRepository.findAll(pageable);
        Page<PersonBasicView> personPage = personRepository.findAllBy(pageable);
        return new PagedResult<>(personPage.getContent(),
                personPage.getTotalElements(),
                personPage.getTotalPages(),
                personPage.getNumber() + 1);
    }

    public PagedResult<Person> getAllPersonEntities(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5, Sort.by("id").ascending());
        Page<Person> personPage = personRepository.findAllEntitiesBy(pageable);
        return new PagedResult<>(personPage.getContent(),
                personPage.getTotalElements(),
                personPage.getTotalPages(),
                personPage.getNumber() + 1);
    }

    public Optional<Person> getPersonById(Long id) {
        return personRepository.findById(id);
    }

    public Person createPerson(CreatePersonRequest personRequest) {
        Person person = new Person(personRequest.name(), personRequest.email(), personRequest.password(),
                personRequest.dob(), true, personRequest.phones());
        return personRepository.save(person);
    }

    public Person updatePerson(UpdatePersonRequest updatePersonRequest) {
        Person person = personRepository.findById(updatePersonRequest.id())
                .orElseThrow(() -> new IllegalArgumentException("Person not found with id: " + updatePersonRequest.id()));
        person.setName(updatePersonRequest.name());
        person.setDob(updatePersonRequest.dob());
        return personRepository.save(person);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    public Optional<Person> login(String email, String password) {
//        return personRepository.findByEmailAndPasswordAndActiveIsTrue(email, password);
        return personRepository.login(email, password);
    }
}
