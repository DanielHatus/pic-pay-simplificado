package com.desafio.picpay.docs;

import com.desafio.picpay.dto.post.DtoPost;
import com.desafio.picpay.dto.put.DtoPut;
import com.desafio.picpay.dto.returns.DtoPersonComplete;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

@Tag(
    name = "PeopleRegister",
    description = "There are 6 methods that perform CRUD (create, read, update and delete) of data present in the database.")
public interface DocControllerPersonControllerCrud{

    @Operation(summary = "the function is to read paged data",
            description ="makes a pagination through the specifications of the customer's orders, if any of the specifications are not " +
            "passed or passed incorrectly, it returns a default value",
            tags = {"PeopleRegister"},
            responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE
                      ,array = @ArraySchema(schema = @Schema(implementation = DtoPersonComplete.class)))),
            @ApiResponse(
               description = "Bad Request",
               responseCode = "400",
               content = @Content),

            @ApiResponse(
                description = "Internal Server Error",
                responseCode = "500",
                content = @Content)
            })
    public ResponseEntity<Page<DtoPersonComplete>> findPageByOrder(Integer page,Integer size,String direction,String orderBy);


    @Operation(
            summary = "takes the entity by id and returns it as json or xml.",
            description = "The entity ID is passed, a check is made to see if the ID actually exists in the database. If it does, " +
                    "the user data for the passed ID is returned via the request body.",
            tags = {"PeopleRegister"},
            responses ={
                    @ApiResponse(
                            description = "Success"
                            , responseCode ="200"
                            ,content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE
                            ,schema = @Schema(implementation = DtoPersonComplete.class)))
                    ,@ApiResponse(
                            description = "Not Found"
                            ,responseCode = "404"
                            ,content = @Content)

                    ,@ApiResponse(
                            description = "Unauthorized"
                            ,responseCode = "401"
                            ,content = @Content)
                    ,@ApiResponse(
                            description = "Internal Server Error"
                            ,responseCode = "401"
                            ,content = @Content)})
    public  ResponseEntity<DtoPersonComplete> findById(Long id);


    @Operation(
            summary = "creates a new user and inserts it into the database.",
            description = "The user's data is passed via the request body to be inserted into the database. Data validation is performed to" +
                    " check whether the data actually meets the API's needs. If any data is incorrectly registered, a bad request and status" +
                    " code 400 are returned.",
            tags = {"PeopleRegister"},
            responses ={
                    @ApiResponse(
                            description = "Created"
                            , responseCode ="201"
                            ,content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE
                            ,schema = @Schema(implementation = DtoPersonComplete.class)))
                    ,@ApiResponse(
                         description = "Bad Request"
                        ,responseCode = "400"
                        ,content = @Content),
                    @ApiResponse(
                         description = "Unauthorized"
                        ,responseCode = "401"
                        ,content = @Content)
                    ,@ApiResponse(
                        description = "Internal Server Error"
                        ,responseCode = "401"
                        ,content = @Content)})
    public ResponseEntity<DtoPersonComplete> createPerson(DtoPost entity);

    @Operation(
            summary = "updates user data.",
            description = "The user data that will be updated is passed through the request body. A check is made to see if the ID passed " +
                    "by the client actually exists in the database. If it does, the data will be updated. However, if the ID does not " +
                    "exist in the database, a bad request and status code 400 are returned.",
            tags = {"PeopleRegister"},
            responses ={
                    @ApiResponse(
                            description = "Success"
                            , responseCode ="200"
                            ,content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE
                            ,schema = @Schema(implementation = DtoPersonComplete.class)))
                    ,@ApiResponse(
                            description = "Bad Request"
                            ,responseCode = "400"
                            ,content = @Content)
                    ,@ApiResponse(
                            description = "Unauthorized"
                            ,responseCode = "401"
                            ,content = @Content)
                    ,@ApiResponse(
                             description = "Internal Server Error"
                        ,responseCode = "401"
                        ,content = @Content)})
    public ResponseEntity<DtoPersonComplete> updateTotalResource(DtoPut entity);

    @Operation(
            summary = "deletes all users from the database.",
            description = "is passed as /All after the controller's base url, after that all the database data is completely deleted, " +
                    "the database remains, but the table data is deleted.",
            tags = {"PeopleRegister"},
            responses ={
                    @ApiResponse(
                            description = "Success"
                            , responseCode ="200"
                            ,content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE
                            ,schema = @Schema(implementation =Void.class)))
                    ,@ApiResponse(
                            description = "Unauthorized"
                            ,responseCode = "401"
                            ,content = @Content)
                    ,@ApiResponse(
                            description = "Internal Server Error"
                            ,responseCode = "401"
                            ,content = @Content)})
    public ResponseEntity<Void> deleteById(Long id);

    @Operation(
            summary = "updates user data.",
            description = "The user data to be updated is passed in the request body in a partial manner, and the ID is passed using a path" +
                    " variable. A check is performed to see if the ID passed by the client actually exists in the database. If so, " +
                    "the passed data will be updated. However, if the ID does not exist in the database, an invalid request and a 400 status" +
                    " code are returned.",
            tags = {"PeopleRegister"},
            responses ={
                    @ApiResponse(
                            description = "Success"
                            , responseCode ="200"
                            ,content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE
                            ,schema = @Schema(implementation = DtoPersonComplete.class)))
                    ,@ApiResponse(
                    description = "Bad Request"
                    ,responseCode = "400"
                    ,content = @Content)
                    ,@ApiResponse(
                    description = "Unauthorized"
                    ,responseCode = "401"
                    ,content = @Content)
                    ,@ApiResponse(
                    description = "Internal Server Error"
                    ,responseCode = "401"
                    ,content = @Content)})
    public ResponseEntity<DtoPersonComplete> updatePartial(Long id, HashMap<String,Object> hashMap);
}
