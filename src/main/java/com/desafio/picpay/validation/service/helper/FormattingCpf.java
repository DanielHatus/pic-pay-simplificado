package com.desafio.picpay.validation.service.helper;

import org.springframework.stereotype.Component;

@Component
public class FormattingCpf{

    public String formatedCpf(String cpf){
        String cpfFormated=cpf.substring(0,3)+"."+cpf.substring(3,6)+"."+cpf.substring(6,9)+"-"+cpf.substring(9);
        return cpfFormated;
    }

    public String unformatedCpf(String cpfFormated){
        String regex="[.-]";
        String cpfUnformated=cpfFormated.replaceAll(regex,"");
        return cpfUnformated;
    }

}
