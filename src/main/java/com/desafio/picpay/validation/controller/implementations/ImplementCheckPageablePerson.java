package com.desafio.picpay.validation.controller.implementations;

import com.desafio.picpay.model.Person;
import com.desafio.picpay.validation.controller.helper.CheckData;
import com.desafio.picpay.validation.controller.interfaces.CheckPageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;

@Component
public class ImplementCheckPageablePerson implements CheckPageable {



    private final CheckData checkData;

    public ImplementCheckPageablePerson(CheckData checkData) {
        this.checkData = checkData;
    }

    @Override
    public Pageable retrunPageable(Integer page, Integer size, String direction, String orderBy) {
        Integer pageReceived=checkPage(page);
        Integer sizeReceived=checkSize(size);
        Sort.Direction directionReceived=checkDirection(direction);
        String orderByReceived=checkOrderBy(orderBy);
        Pageable pageable= PageRequest.of(pageReceived,sizeReceived,directionReceived,orderByReceived);
        return pageable;
    }

    private Integer checkPage(Integer page){
       return (checkData.dataIsNull(page)|| page<=0)?0:page-1;
    }

    private Integer checkSize(Integer size){
        return (checkData.dataIsNull(size)|| size<=0)?1:size;
    }

    private Sort.Direction checkDirection(String direction){
        if ( checkData.dataIsNull(direction)|| !direction.equalsIgnoreCase("desc")) return Sort.Direction.ASC;
        return Sort.Direction.DESC;
    }

    private String checkOrderBy(String orderBy){
        Class<Person> classRequired= Person.class;
        Field[] fields=classRequired.getFields();
        ArrayList<String> typesRequiredOrder=new ArrayList<String>();
        for (Field field:fields){
            typesRequiredOrder.add(field.getName());
        }

        if (checkData.dataIsNull(orderBy)||!typesRequiredOrder.contains(orderBy.toLowerCase())) return "name";
        return orderBy;
    }
}
