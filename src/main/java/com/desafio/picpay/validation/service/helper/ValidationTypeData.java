package com.desafio.picpay.validation.service.helper;

import org.springframework.stereotype.Component;

@Component
public class ValidationTypeData{

    public<TypeData,TypeRequired> boolean typeIsCorrect(TypeData data,Class<TypeRequired> classPointer){
        return classPointer.isInstance(data);
    }
}
