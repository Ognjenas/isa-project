package com.isateam.blooddonationcenter.core.validationhandlers;

import com.isateam.blooddonationcenter.core.errorhandling.BaseException;
import com.isateam.blooddonationcenter.core.errorhandling.NotFoundException;
import com.isateam.blooddonationcenter.core.errorhandling.StandardErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> notFoundExceptionHandler(BaseException exception) {
        System.out.print(exception.getStackTrace());
        return new ResponseEntity<>(new StandardErrorResponse(exception), exception.getStatusCode());
    }
}
