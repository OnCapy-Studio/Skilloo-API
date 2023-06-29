package com.skilloo.api.services.validation;

import com.skilloo.api.controllers.exceptions.validation.FieldMessage;
import com.skilloo.api.dto.user.admin.UserAdminInsertDTO;
import com.skilloo.api.entities.User;
import com.skilloo.api.repositories.ProfessorRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserAdminInsertValidator implements ConstraintValidator<UserAdminInsertValid, UserAdminInsertDTO> {

    @Autowired
    private ProfessorRepository userRepository;

    @Override
    public void initialize(UserAdminInsertValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserAdminInsertDTO value, ConstraintValidatorContext context) {

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
