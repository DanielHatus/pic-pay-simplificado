package com.desafio.picpay.dto.returns;

import java.util.Objects;

public class DtoPersonComplete{
    private String name;
    private String email;
    private String cpf;
    private Integer password;
    private String typeUser;
    private Double statement;
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

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
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

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {

        if (!(o instanceof DtoPersonComplete that)) return false;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getCpf(), that.getCpf()) && Objects.equals(getPassword(), that.getPassword()) && Objects.equals(getTypeUser(), that.getTypeUser()) && Objects.equals(getStatement(), that.getStatement()) && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getEmail(), getCpf(), getPassword(), getTypeUser(), getStatement(), getId());
    }
}
