package com.skilloo.api.services.validation.materia;

import com.skilloo.api.controllers.exceptions.validation.FieldMessage;
import com.skilloo.api.dto.MateriaDTO;
import com.skilloo.api.dto.user.UserInsertDTO;
import com.skilloo.api.entities.Materia;
import com.skilloo.api.entities.User;
import com.skilloo.api.repositories.MateriaRepository;
import com.skilloo.api.repositories.ProfessorRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class MateriaValidator implements ConstraintValidator<MateriaValid, MateriaDTO> {

    @Autowired
    private MateriaRepository materiaRepository;


    @Override
    public void initialize(MateriaValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MateriaDTO dto, ConstraintValidatorContext context) {

        //lista de erros
        List<FieldMessage> list = new ArrayList<>();


        //lógica para vefiricar se o email existe ou não
        var materia = (Materia) materiaRepository.findByNome(dto.getNome());

        //caso volte um user
        if(materia != null){
            list.add(new FieldMessage("Nome", "Materia já existente"));
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
