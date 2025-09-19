package com.desafio.picpay.exceptions.handler;

import com.desafio.picpay.exceptions.responsegeneric.ResponseGeneric;
import com.desafio.picpay.exceptions.types.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class NotFoundResponse{
    @ExceptionHandler(NotFound.class)
    public ResponseEntity<ResponseGeneric> responseHandler(NotFound e){
        HttpStatus status=HttpStatus.NOT_FOUND;
        ResponseGeneric responseGeneric=new ResponseGeneric(e.getMessage(),status.name(),status.value(), LocalDateTime.now());
        return new ResponseEntity<>(responseGeneric,status);
    }
}
