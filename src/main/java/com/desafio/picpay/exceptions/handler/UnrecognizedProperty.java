package com.desafio.picpay.exceptions.handler;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class UnrecognizedProperty {
    @ExceptionHandler(UnrecognizedPropertyException.class)

    public ResponseEntity<Map<String,String>> handlerResponse(UnrecognizedPropertyException e){
        HttpStatus status=HttpStatus.BAD_REQUEST;
        String errorMessage=String.format("Unknown field in request: %s .",e.getPropertyName());
        Map<String,String> responseBody=Map.of("error","Request with invalid fields","details",errorMessage);
        return new ResponseEntity<>(responseBody,status);
    }
}
