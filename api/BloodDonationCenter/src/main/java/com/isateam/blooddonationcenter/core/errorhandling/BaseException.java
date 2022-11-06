package com.isateam.blooddonationcenter.core.errorhandling;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends Exception{

    protected HttpStatus statusCode;

    public BaseException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
