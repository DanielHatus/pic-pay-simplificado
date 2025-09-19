package com.desafio.picpay.service;

import com.desafio.picpay.dto.post.DtoPost;
import com.desafio.picpay.dto.put.DtoPut;
import com.desafio.picpay.dto.returns.DtoPersonComplete;
import com.desafio.picpay.exceptions.types.BadRequest;
import com.desafio.picpay.exceptions.types.NotFound;
import com.desafio.picpay.mapper.MapperDozzer;
import com.desafio.picpay.model.Person;
import com.desafio.picpay.repository.PersonRepository;
import com.desafio.picpay.validation.repository.*;
import com.desafio.picpay.validation.service.helper.FormattingCpf;
import com.desafio.picpay.validation.service.interfaces.ValidationPassword;
import com.desafio.picpay.validation.service.interfaces.ValidationPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class PeopleRegisterService{

    private final PersonRepository repository;
    private final ValidationIdRepositoryPerson validationId;
    private final MapperDozzer mapper;
    private final FormattingCpf formattingCpf;
    private final ValidationPassword validationPassword;
    private final ValidationPatch validationPatch;
    @Autowired
    public PeopleRegisterService(
            PersonRepository repository,
            ValidationIdRepositoryPerson validationId,
            MapperDozzer mapper,
            @Qualifier("implementValidationPasswordPerson") ValidationPassword validationPassword,
            @Qualifier("implementValidationPatchPerson") ValidationPatch validationPatch,
            FormattingCpf formattingCpf) {

            this.repository=repository;
            this.validationId=validationId;
            this.mapper=mapper;
            this.validationPassword=validationPassword;
            this.formattingCpf=formattingCpf;
            this.validationPatch=validationPatch;

    }


    public Page<DtoPersonComplete> findAllPageByOrder(Pageable pageable){
      return mapper.mapperPage(repository.findAll(pageable),DtoPersonComplete.class);
    }

    public DtoPersonComplete findById(Long id){
     if (validationId.IdIsValid(id)){
         return mapper.mapperObject(repository.findById(id).get(), DtoPersonComplete.class);
     }
     throw new NotFound("Id Not Found In Database .");
    }

    public DtoPersonComplete createPerson(DtoPost entityDto){
        if (validationPassword.PasswordIsCorrect(entityDto.getPassword())){
            entityDto.setCpf(formattingCpf.formatedCpf(entityDto.getCpf()));
            Person entity=mapper.mapperObject(entityDto,Person.class);
            return mapper.mapperObject(repository.save(entity),DtoPersonComplete.class);
        }
        throw new BadRequest("Password Is Invalid.");
    }

    public DtoPersonComplete updatePerson(DtoPut entityDto){
    if (validationId.IdIsValid(entityDto.getId())&&validationPassword.PasswordIsCorrect(entityDto.getPassword())){
        Person entity=mapper.mapperObject(entityDto,Person.class);
        return mapper.mapperObject(repository.save(entity),DtoPersonComplete.class);
    }
    throw new NotFound("Id Not Found.");
    }

    public DtoPersonComplete updatePartial(Long id, HashMap<String,Object> hashMap){
        return mapper.mapperObject(repository.save(validationPatch.validationMethodPatch(id,hashMap)),DtoPersonComplete.class);
    }

    public void deleteById(Long id){
        if(validationId.IdIsValid(id)){
            repository.deleteById(id);
        }
        throw new NotFound("Id Not Found in Database.");
    }
}
