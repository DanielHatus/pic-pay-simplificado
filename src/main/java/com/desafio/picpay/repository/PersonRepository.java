package com.desafio.picpay.repository;

import com.desafio.picpay.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface  PersonRepository extends JpaRepository<Person,Long>{

    Optional<Person> findByNameIgnoreCase(String name);

    public boolean existsByEmail(String email);

    public boolean existsByCpf(String cpfFormated);


}
