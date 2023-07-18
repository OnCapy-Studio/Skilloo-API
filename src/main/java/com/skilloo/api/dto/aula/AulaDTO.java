package com.skilloo.api.dto.aula;

import com.skilloo.api.entities.Aula;
import com.skilloo.api.entities.Materia;
import com.skilloo.api.entities.Turma;
import com.skilloo.api.entities.enuns.Dia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AulaDTO {

    private Long id;
    private Materia materia;
    private DayOfWeek dia;
    private String horario;
    private Turma turma;

    public AulaDTO(Aula aula) {
        id = aula.getId();
        materia = aula.getMateria();
        dia = aula.getDia();
        horario = aula.getHorario().toString();
        turma = aula.getTurma();
    }
}
