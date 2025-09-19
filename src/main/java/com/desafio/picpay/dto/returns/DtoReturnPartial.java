package com.desafio.picpay.dto.returns;

import java.util.Objects;

public class DtoReturnPartial{
    private String name;
    private String email;
    private String statement;

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

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    @Override
    public boolean equals(Object o) {

        if (!(o instanceof DtoReturnPartial that)) return false;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getStatement(), that.getStatement());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getEmail(), getStatement());
    }
}
