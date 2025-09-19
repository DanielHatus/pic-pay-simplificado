package com.desafio.picpay.controller;


import com.desafio.picpay.docs.DocControllerPersonControllerCrud;
import com.desafio.picpay.dto.post.DtoPost;
import com.desafio.picpay.dto.put.DtoPut;
import com.desafio.picpay.dto.returns.DtoPersonComplete;
import com.desafio.picpay.service.PeopleRegisterService;
import com.desafio.picpay.validation.controller.interfaces.CheckPageable;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/crud")
public class PersonControllerCrud implements DocControllerPersonControllerCrud {

    private final PeopleRegisterService peopleRegisterService;
    private final CheckPageable checkPageablePerson;

    @Autowired
    public PersonControllerCrud(
            PeopleRegisterService peopleRegisterService,
            @Qualifier("implementCheckPageablePerson") CheckPageable checkPageablePerson){

        this.peopleRegisterService = peopleRegisterService;
            this.checkPageablePerson = checkPageablePerson;
    }


    @Override
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})

    public ResponseEntity<Page<DtoPersonComplete>> findPageByOrder(
         @RequestParam(required = false) Integer page,
         @RequestParam(required = false) Integer size,
         @RequestParam(required = false)  String direction,
         @RequestParam(required = false)  String orderBy) {

        Pageable pageable=checkPageablePerson.retrunPageable(page,size,direction,orderBy);
        return ResponseEntity.ok(peopleRegisterService.findAllPageByOrder(pageable));
    }

    @Override
    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})

    public ResponseEntity<DtoPersonComplete> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(peopleRegisterService.findById(id));
    }

    @Override
    @PostMapping(
            consumes ={MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
            produces ={MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})

    public ResponseEntity<DtoPersonComplete> createPerson( @RequestBody DtoPost entity) {
        return ResponseEntity.created(null).body(peopleRegisterService.createPerson(entity));
    }

    @Override
    @PutMapping(
            consumes ={MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
            produces ={MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<DtoPersonComplete> updateTotalResource( @RequestBody DtoPut entity) {
        return ResponseEntity.ok(peopleRegisterService.updatePerson(entity));

    }

    @Override
    @DeleteMapping(value = "/{id}")

    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id){
        peopleRegisterService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PatchMapping(
            value = "/{id}",
            consumes ={MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
            produces ={MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<DtoPersonComplete> updatePartial(@PathVariable("id") Long id, @RequestBody HashMap<String, Object> hashMap) {
        return ResponseEntity.ok(peopleRegisterService.updatePartial(id,hashMap));
    }
}
