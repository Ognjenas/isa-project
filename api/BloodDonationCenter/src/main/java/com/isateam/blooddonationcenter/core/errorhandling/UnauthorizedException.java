package com.isateam.blooddonationcenter.core.errorhandling;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends BaseException{
    public UnauthorizedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
