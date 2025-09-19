package com.desafio.picpay.validation.controller.helper;

import org.springframework.stereotype.Component;

@Component
public class CheckData{

    public<T> boolean dataIsNull(T data){
        return data==null;
    }
}
