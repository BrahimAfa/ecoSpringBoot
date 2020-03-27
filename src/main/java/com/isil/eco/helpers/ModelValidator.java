package com.isil.eco.helpers;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class ModelValidator {
    public static String getErrorsFromBindingResult(BindingResult bindingResult){
        StringBuilder errorStringBuilder= new StringBuilder();
        bindingResult.getAllErrors().forEach(obj->errorStringBuilder.append(obj.getDefaultMessage()).append("-"));
        return errorStringBuilder.toString();
    }
}
