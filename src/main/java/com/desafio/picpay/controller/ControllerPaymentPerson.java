package com.desafio.picpay.controller;

import com.desafio.picpay.docs.DocControllerPaymentPerson;
import com.desafio.picpay.dto.payment.DtoPaymentPerson;
import com.desafio.picpay.dto.returns.DtoReturnPartial;
import com.desafio.picpay.service.PaymentService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class ControllerPaymentPerson implements DocControllerPaymentPerson{
    private final PaymentService service;

    public ControllerPaymentPerson(PaymentService service) {
        this.service = service;
    }

    @Override
    @PatchMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},produces ={MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE} )
    public ResponseEntity<List<DtoReturnPartial>> makingPayment( @RequestBody DtoPaymentPerson entity) {
        return ResponseEntity.ok(service.paymentUser(entity));
    }
}
