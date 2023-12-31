package com.example.deliveryservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnAuthorizeRequestAlertException extends RuntimeException{
    private static final long serialVersionUID = 1L;


    private String message;

    private HttpStatus httpStatus;

    public UnAuthorizeRequestAlertException(String message, HttpStatus httpStatus){
        this.message=message;
        this.httpStatus=httpStatus;
    }



}
