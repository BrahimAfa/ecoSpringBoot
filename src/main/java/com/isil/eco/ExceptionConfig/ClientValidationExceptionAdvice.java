package com.isil.eco.ExceptionConfig;

import com.isil.eco.Exceptions.ClientValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ClientValidationExceptionAdvice {

        @ResponseBody
        @ExceptionHandler(ClientValidationException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        String employeeNotFoundHandler(ClientValidationException ex) {
            return ex.getMessage();
        }

}
