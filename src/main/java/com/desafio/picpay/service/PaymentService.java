package com.desafio.picpay.service;

import com.desafio.picpay.dto.payment.DtoPaymentPerson;

import com.desafio.picpay.dto.returns.DtoReturnPartial;
import com.desafio.picpay.validation.service.payment.LogicPayment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;


import java.util.List;

@Service
public class PaymentService{
    private final LogicPayment logicPayment;

    public PaymentService(LogicPayment logicPayment) {
        this.logicPayment = logicPayment;
    }
    
    public List<DtoReturnPartial> paymentUser(DtoPaymentPerson dtoEntity){
      return logicPayment.makingPayment(dtoEntity);
    }
}
