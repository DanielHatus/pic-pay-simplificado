package com.desafio.picpay.validation.service.implementations;

import com.desafio.picpay.validation.service.interfaces.ValidationPassword;
import org.springframework.stereotype.Component;

@Component
public class ImplementValidationPasswordPerson implements ValidationPassword {
    @Override
    public boolean PasswordIsCorrect(Integer password) {
        String passwordConverted=password+"";
         return passwordConverted.length()==4;
    }
}
