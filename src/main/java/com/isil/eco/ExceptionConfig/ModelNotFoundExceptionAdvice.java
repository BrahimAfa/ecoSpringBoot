package com.isil.eco.ExceptionConfig;

import com.isil.eco.Exceptions.ModelNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ModelNotFoundExceptionAdvice {

        @ResponseBody
        @ExceptionHandler(ModelNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        String ModelNotFoundHandler(ModelNotFoundException ex) {
            return ex.getMessage();
        }

}
