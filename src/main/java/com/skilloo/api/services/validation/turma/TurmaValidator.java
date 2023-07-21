package com.skilloo.api.services.validation.turma;

import com.skilloo.api.controllers.exceptions.validation.FieldMessage;
import com.skilloo.api.dto.MateriaDTO;
import com.skilloo.api.dto.TurmaDTO;
import com.skilloo.api.entities.Materia;
import com.skilloo.api.entities.Turma;
import com.skilloo.api.repositories.MateriaRepository;
import com.skilloo.api.repositories.TurmaRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TurmaValidator implements ConstraintValidator<TurmaValid, TurmaDTO> {

    @Autowired
    private TurmaRepository turmaRepository;


    @Override
    public void initialize(TurmaValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(TurmaDTO dto, ConstraintValidatorContext context) {

        //lista de erros
        List<FieldMessage> list = new ArrayList<>();

        var turma = (Turma) turmaRepository.findByNome(dto.getNome());

        if(turma != null){
            list.add(new FieldMessage("Nome", "Turma já existente"));
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
