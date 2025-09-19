package com.desafio.picpay.model;


import com.desafio.picpay.enums.StatusTypeClient;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Objects;

@Entity
@Table(name = "clients")
public class Person{

    private String name;


    private String email;


    private String cpf;

    private Integer password;

    @Enumerated(EnumType.STRING)
    private StatusTypeClient typeUser;

    private Double statement;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public StatusTypeClient getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(StatusTypeClient typeUser) {
        this.typeUser = typeUser;
    }

    public Double getStatement() {
        return statement;
    }

    public void setStatement(Double statement) {
        this.statement = statement;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {

        if (!(o instanceof Person person)) return false;
        return Objects.equals(getName(), person.getName()) && Objects.equals(getEmail(), person.getEmail()) && Objects.equals(getCpf(), person.getCpf()) && Objects.equals(getPassword(), person.getPassword()) && Objects.equals(getTypeUser(), person.getTypeUser()) && Objects.equals(getStatement(), person.getStatement()) && Objects.equals(getId(), person.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getEmail(), getCpf(), getPassword(), getTypeUser(), getStatement(), getId());
    }
}
