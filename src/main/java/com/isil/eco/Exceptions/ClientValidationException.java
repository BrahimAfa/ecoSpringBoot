package com.isil.eco.Exceptions;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class ClientValidationException extends RuntimeException  {
    public ClientValidationException(String err) {
        super(err);

    }

}
