package com.capgemini.services.validators.impls;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


import com.capgemini.services.validators.ClienteValidatorImp;

@Target(ElementType.TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = ClienteValidatorImp.class)
@Documented
public @interface IClienteValidator {

	String message() default "Fallo validacion campos de cliente";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
