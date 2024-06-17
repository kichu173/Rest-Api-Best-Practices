package com.sivalabs.myapp.web;

import com.sivalabs.myapp.entities.Person;
import com.sivalabs.myapp.models.CreatePersonRequest;
import com.sivalabs.myapp.models.PagedResult;
import com.sivalabs.myapp.models.PersonBasicView;
import com.sivalabs.myapp.models.UpdatePersonRequest;
import com.sivalabs.myapp.models.UpdatePersonWebRequest;
import com.sivalabs.myapp.services.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(final PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public PagedResult<PersonBasicView> getAllPersons(@RequestParam(defaultValue = "1") int pageNo) {
        return personService.getAllPersons(pageNo);
    }

    @GetMapping("/all")// N+1 problem
    public PagedResult<Person> getAllPersons1(@RequestParam(defaultValue = "1") int pageNo) {
        return personService.getAllPersonEntities(pageNo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        return personService.getPersonById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Person> createPerson(@RequestBody @Validated CreatePersonRequest personRequest) {
        return new ResponseEntity<>(personService.createPerson(personRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable Long id, @RequestBody @Validated UpdatePersonWebRequest updatePersonWebRequest) {
        UpdatePersonRequest updatePersonRequest = new UpdatePersonRequest(id, updatePersonWebRequest.name(), updatePersonWebRequest.dob());
        return personService.updatePerson(updatePersonRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
    }
}
