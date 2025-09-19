package com.desafio.picpay.validation.controller.interfaces;

import org.springframework.data.domain.Pageable;

public interface CheckPageable{

    public Pageable retrunPageable(Integer page,Integer size,String direction,String orderBy);
}
