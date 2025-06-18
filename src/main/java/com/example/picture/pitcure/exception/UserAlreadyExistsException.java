package com.example.picture.pitcure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserAlreadyExistsException extends  Exception{

    public UserAlreadyExistsException(String message){
        super(message);
    }
}
