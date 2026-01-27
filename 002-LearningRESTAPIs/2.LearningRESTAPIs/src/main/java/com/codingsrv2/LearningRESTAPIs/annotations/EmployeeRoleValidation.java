package com.codingsrv2.LearningRESTAPIs.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Constraint(validatedBy = {EmployeeValidator.class})
public @interface EmployeeRoleValidation {
    String message() default "Employee can be either Admin or Use";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
