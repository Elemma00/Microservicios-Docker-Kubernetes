package com.emma.springcloud.mscv.usuarios.msvc_usuarios.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistsByEmailValidation.class)
public @interface ExistsByEmail {

   String message() default "ya esta asociado a un usuario";

   Class<?>[] groups() default {};

   Class<? extends Payload>[] payload() default {};
}