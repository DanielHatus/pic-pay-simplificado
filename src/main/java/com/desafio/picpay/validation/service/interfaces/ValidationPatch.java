package com.desafio.picpay.validation.service.interfaces;

import com.desafio.picpay.model.Person;

import java.util.HashMap;

public interface ValidationPatch {

    public Person validationMethodPatch(Long id, HashMap<String,Object> hashMap);
}
