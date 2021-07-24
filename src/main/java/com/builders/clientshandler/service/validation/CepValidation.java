package com.builders.clientshandler.service.validation;

import com.builders.clientshandler.service.validation.constraints.Cep;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class CepValidation implements ConstraintValidator<Cep, String> {

    @Override
    public void initialize(Cep constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern p = Pattern.compile("^\\d{5}-\\d{3}$");
        return p.matcher(value).matches();
    }
}
