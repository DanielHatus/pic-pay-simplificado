package com.desafio.picpay.validation.service.payment;


import com.desafio.picpay.dto.payment.DtoPaymentPerson;
import com.desafio.picpay.dto.returns.DtoPersonComplete;
import com.desafio.picpay.dto.returns.DtoReturnPartial;
import com.desafio.picpay.enums.StatusTypeClient;
import com.desafio.picpay.exceptions.types.BadRequest;
import com.desafio.picpay.mapper.MapperDozzer;
import com.desafio.picpay.model.Person;
import com.desafio.picpay.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class LogicPayment{

    private final PersonRepository repository;
    private final MapperDozzer mapper;

    public LogicPayment(PersonRepository repository, MapperDozzer mapper) {
        this.repository = repository;
        this.mapper=mapper;
    }

    private Person getPayer(String name){
        Optional<Person> payer=repository.findByNameIgnoreCase(name);
        if (payer.isEmpty()){
            throw new BadRequest("User Payer Not Found in Database");
        }
        return payer.get();
    }

    private Person getPayee(String name){
        Optional<Person> payee=repository.findByNameIgnoreCase(name);
        if (payee.isEmpty()){
            throw new BadRequest("User payee Not Found in Database");
        }
        return payee.get();
    }

    @Transactional
    public List<DtoReturnPartial> makingPayment(DtoPaymentPerson entityPayment){

        Person payer=getPayer(entityPayment.getPayer());
        Person payee=getPayee(entityPayment.getPayee());
        Double valuePayment= entityPayment.getValue();

        if (checkPayment(payer.getStatement(),valuePayment)&&checkTypeUser(payer.getTypeUser())){
            payer.setStatement(payer.getStatement()-valuePayment);
            payee.setStatement(payee.getStatement()+valuePayment);
            List<Person> list=List.of(payer,payee);
            repository.saveAll(List.of(payer,payee));
            return mapper.mapperList(list, DtoReturnPartial.class);
        }

       throw new BadRequest("It was not possible to make the transfer.");
    }

    private boolean checkPayment(Double statementPayer,Double value){
       Double statementpayerEntity=statementPayer;
       Double valuePayment=value;
       if (valuePayment>statementpayerEntity){
           throw new BadRequest("the payment amount "+valuePayment+" is greater than your bank statement");
       }

       else if (valuePayment<=0){
       throw new BadRequest("It is not possible to transfer a value less than or equal to 0.");
       }

       else{
           return true;
       }
    }

    private boolean checkTypeUser(StatusTypeClient typeUser){
        if (typeUser.equals(StatusTypeClient.USER)){
            return true;
        }
        throw new BadRequest(" is Type Is Invalid.");
    }


}
