package com.desafio.picpay.dto.post;

import com.desafio.picpay.enums.StatusTypeClient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

@JsonIgnoreProperties(ignoreUnknown = false)
public class DtoPost {

    @NotBlank
    private String name;

    @Email
    @NotNull
    private String email;

    @CPF
    @NotNull
    private String cpf;

    @NotNull
    private Integer password;

    @NotNull
    private StatusTypeClient typeUser;

    @NotNull
    private Double statement;



    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public Integer getPassword() {
        return password;
    }

    public StatusTypeClient getTypeUser() {
        return typeUser;
    }

    public Double getStatement() {
        return statement;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public void setTypeUser(StatusTypeClient typeUser) {
        this.typeUser = typeUser;
    }

    public void setStatement(Double statement) {
        this.statement = statement;
    }
}
