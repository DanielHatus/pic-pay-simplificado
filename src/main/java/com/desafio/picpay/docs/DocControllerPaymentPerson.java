package com.desafio.picpay.docs;

import com.desafio.picpay.dto.payment.DtoPaymentPerson;
import com.desafio.picpay.dto.returns.DtoReturnPartial;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Payment",
  description = "This controller is responsible for carrying out the route that will make the bank transfer.")
public interface DocControllerPaymentPerson{
    @Operation(
            summary = "makes payment between 2 users.",
            description = "is responsible for making the payment, the name of the payer, the name of the beneficiary who will receive" +
                    " the bank transfer, and the amount of the transfer are passed. If the payer or beneficiary is not in the database," +
                    " a Bad Request exception will be passed, and if the amount passed is greater than the bank statement" +
                    ", an exception will also be passed.",
            tags = {"Payment"},
            responses ={
                    @ApiResponse(
                            description = "Success"
                            , responseCode ="200"
                            ,content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE
                            ,schema = @Schema(implementation =DtoReturnPartial.class))
                    ),@ApiResponse(
                    description = "Bad Request"
                    ,responseCode = "400"
                    ,content = @Content
            ),@ApiResponse(
                    description = "Internal Server Error"
                    ,responseCode = "500"
                    ,content = @Content
            )
            }

    )
    public ResponseEntity<List<DtoReturnPartial>> makingPayment(DtoPaymentPerson entity);


}
