package com.desafio.picpay.validation.service.implementations;

import com.desafio.picpay.enums.StatusTypeClient;
import com.desafio.picpay.exceptions.types.BadRequest;
import com.desafio.picpay.exceptions.types.NotFound;
import com.desafio.picpay.model.Person;
import com.desafio.picpay.repository.PersonRepository;
import com.desafio.picpay.validation.repository.ValidationIdRepositoryPerson;
import com.desafio.picpay.validation.service.helper.FormattingCpf;
import com.desafio.picpay.validation.service.helper.ValidationTypeData;
import com.desafio.picpay.validation.service.interfaces.ValidationPassword;
import com.desafio.picpay.validation.service.interfaces.ValidationPatch;

import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ImplementValidationPatchPerson implements ValidationPatch {

    private final ValidationIdRepositoryPerson repositoryValidation;
    private final PersonRepository repository;
    private final ValidationTypeData validationTypeData;
    private final FormattingCpf formattingCpf;
    private final ValidationPassword validationPassword;

    @CPF
    private String cpf;

    @Email
    private String email;

    @Autowired
    public ImplementValidationPatchPerson(
            ValidationIdRepositoryPerson repositoryValidation,
            PersonRepository repository,
            ValidationTypeData validationTypeData,
            FormattingCpf formattingCpf,
            @Qualifier("implementValidationPasswordPerson") ValidationPassword validationPassword){

           this.repositoryValidation = repositoryValidation;
           this.repository=repository;
           this.validationTypeData=validationTypeData;
           this.formattingCpf=formattingCpf;
           this.validationPassword=validationPassword;
    }

    @Override
    public Person validationMethodPatch(Long id, HashMap<String, Object> hashMap) {
       if (!repositoryValidation.IdIsValid(id)){
           throw new NotFound("Id Not Found In Database");
       }

       Person entity=repository.findById(id).get();


       hashMap.forEach((key,value)->{
           switch (key.toLowerCase()){
               case "name":
                   if (validationTypeData.typeIsCorrect(value,String.class)){
                       entity.setName((String) value);
                       break;
                   }
                   throw new BadRequest("the data type"+value+" is invalid because it is not a string.");

               case "email":
                   if (validationTypeData.typeIsCorrect(value,String.class)){
                       setEmail((String) value);
                       entity.setEmail((String) value);
                       break;
                   }
                   throw new BadRequest("the data type"+value+" is invalid because it is not a string.");

               case "cpf":
                   if (validationTypeData.typeIsCorrect(value, String.class)){
                       setCpf((String) cpf);
                       entity.setCpf(formattingCpf.formatedCpf((String) value));
                       break;
                   }
                   throw new BadRequest("the data type"+value+" is invalid because it is not a string.");

               case "password":
                   if (validationTypeData.typeIsCorrect(value,Integer.class)){
                     if (validationPassword.PasswordIsCorrect((Integer) value)){
                         entity.setPassword((Integer) value);
                         break;
                     }
                     throw new BadRequest("Password Is Incorrect");
                   }
                   throw new BadRequest("the data type"+value+" is invalid because it is not a Integer.");

               case "typeuser":
                   if (validationTypeData.typeIsCorrect(value,String.class)){
                       if(!StatusTypeClient.enumIsNull((String) value)){
                           entity.setTypeUser(StatusTypeClient.treatingReturnString((String) value));
                           break;
                       }
                       throw new BadRequest("value "+value+" is incorrect.");
                   }
                   throw new BadRequest("the data type"+value+" is invalid because it is not a String.");

               case "statement":
                   if (validationTypeData.typeIsCorrect(value, Double.class)){
                       if ((Double) value>=0){
                           entity.setStatement((Double) value);
                           break;
                       }
                       throw  new BadRequest("the value:"+value+" cannot be less than 0");
                   }
                   throw new BadRequest("the data type"+value+" is invalid because it is not a Double.");


               default:
                   throw new BadRequest("an error occurred, please try again later.");
           }
       });
       return entity;
    }

    private void setCpf(String cpf) {
        this.cpf = cpf;
    }

    private void setEmail(String email) {
        this.email = email;
    }
}
