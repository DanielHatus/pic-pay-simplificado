package com.desafio.picpay.validation.repository;

import com.desafio.picpay.repository.PersonRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidationIdRepositoryPerson {
    private final PersonRepository repository;

    public ValidationIdRepositoryPerson(PersonRepository repository) {
        this.repository = repository;
    }

    public boolean IdIsValid(Long id){
        return repository.findById(id).isPresent();
    }


}
