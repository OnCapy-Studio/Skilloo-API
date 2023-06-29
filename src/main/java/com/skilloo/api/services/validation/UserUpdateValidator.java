package com.skilloo.api.services.validation;

import com.skilloo.api.controllers.exceptions.validation.FieldMessage;
import com.skilloo.api.dto.user.UserUpdateDTO;
import com.skilloo.api.entities.User;
import com.skilloo.api.repositories.ProfessorRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserUpdateDTO> {

    @Autowired
    private ProfessorRepository userRepository;

    @Autowired
    private HttpServletRequest request;

    @Override
    public void initialize(UserUpdateValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserUpdateDTO value, ConstraintValidatorContext context) {


        //buscando o id do usuario pela requisição
        @SuppressWarnings("unchecked")
        var uriVars = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        long userId = Long.parseLong(uriVars.get("id"));

        //lista de erros
        List<FieldMessage> list = new ArrayList<>();


        //lógica para vefiricar se o email existe ou não
        var user = (User) userRepository.findByEmail(value.getEmail());


        //caso volte um usuário E o id do usuário seja diferente do Id passado no DTO
        if(user != null && userId != user.getId()){
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
