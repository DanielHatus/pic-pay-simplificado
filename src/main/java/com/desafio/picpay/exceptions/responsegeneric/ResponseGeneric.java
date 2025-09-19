package com.desafio.picpay.exceptions.responsegeneric;

import java.time.LocalDateTime;

public record ResponseGeneric(String message, String statusname, int statuscode, LocalDateTime timestamp){
}
