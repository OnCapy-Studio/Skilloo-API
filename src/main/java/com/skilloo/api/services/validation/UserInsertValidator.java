package com.skilloo.api.services.validation;

import com.skilloo.api.controllers.exceptions.validation.FieldMessage;
import com.skilloo.api.dto.user.UserInsertDTO;
import com.skilloo.api.entities.User;
import com.skilloo.api.entities.enuns.Role;
import com.skilloo.api.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {

    @Autowired
    private UserRepository userRepository;


    @Override
    public void initialize(UserInsertValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserInsertDTO value, ConstraintValidatorContext context) {

        //lista de erros
        List<FieldMessage> list = new ArrayList<>();


        //lógica para vefiricar se o email existe ou não
        var user = (User) userRepository.findByEmail(value.getEmail());

        //caso volte um user
        if(user != null){
            list.add(new FieldMessage("Email", "Email já existente"));
        }


        //atribuindo os erros à lista do spring
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getField())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
