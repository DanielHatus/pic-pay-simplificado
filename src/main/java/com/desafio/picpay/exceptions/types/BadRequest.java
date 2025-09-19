package com.desafio.picpay.exceptions.types;

public class BadRequest extends RuntimeException {
    public BadRequest(String message) {
        super(message);
    }
}
