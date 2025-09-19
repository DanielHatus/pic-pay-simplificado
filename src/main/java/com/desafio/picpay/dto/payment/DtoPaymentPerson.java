package com.desafio.picpay.dto.payment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DtoPaymentPerson{
    @NotBlank
    private String payee;

    @NotBlank
    private String payer;

    @NotNull
    private Double value;

    public String getPayee() {
        return payee;
    }

    public String getPayer() {
        return payer;
    }

    public Double getValue() {
        return value;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
