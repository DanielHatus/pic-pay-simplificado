package com.desafio.picpay.enums;

import com.desafio.picpay.exceptions.types.BadRequest;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum StatusTypeClient{
    USER,
    STOREKEEPER;


    @JsonCreator
    public static StatusTypeClient treatingReturnString(String valueReceived){
        if (enumIsNull(valueReceived)){
            return null;
        }

        for (StatusTypeClient status:StatusTypeClient.values()){
            if (status.name().equalsIgnoreCase(valueReceived)) return status;
        }
        throw new BadRequest("is value: "+valueReceived+" is Incorrect.");
    }

    public static boolean enumIsNull(String valueReceived){
        return valueReceived==null;
    }
}
