package com.crudapplication.user_api.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidEmailException extends RuntimeException{
    
    public InvalidEmailException(String message) {
        super(message);
    }
}
