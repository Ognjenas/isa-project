package com.isateam.blooddonationcenter.core.errorhandling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class StandardErrorResponse {
    private String message;
    private int statusCode;


    public StandardErrorResponse(BaseException exception) {
        this.message = exception.getMessage();
        this.statusCode = exception.getStatusCode().value();
    }
}
