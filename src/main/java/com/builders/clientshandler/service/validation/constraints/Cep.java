package com.builders.clientshandler.service.validation.constraints;

import com.builders.clientshandler.service.validation.CepValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {CepValidation.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
public @interface Cep {

    String message() default "CEP inválido!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
