package com.emma.springcloud.mscv.usuarios.msvc_usuarios.validations;

import org.springframework.beans.factory.annotation.Autowired;

import com.emma.springcloud.mscv.usuarios.msvc_usuarios.services.UsuarioService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExistsByEmailValidation implements ConstraintValidator<ExistsByEmail, String> {

    @Autowired
    private UsuarioService userService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if(userService == null) return true;

        return !userService.existeEmail(email);
    }

}
