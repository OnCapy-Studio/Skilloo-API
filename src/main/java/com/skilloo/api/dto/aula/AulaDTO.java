package com.skilloo.api.dto.aula;

import com.skilloo.api.dto.MateriaDTO;
import com.skilloo.api.dto.TurmaDTO;
import com.skilloo.api.dto.user.SimpleUserDTO;
import com.skilloo.api.dto.user.UserDTO;
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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AulaDTO {

    private Long id;
    private DayOfWeek dia;
    private LocalTime horario;
    private MateriaDTO materia;
    private TurmaDTO turma;
    private List<SimpleUserDTO> professores;

    public AulaDTO(Aula aula) {
        id = aula.getId();
        materia = new MateriaDTO(aula.getMateria());
        dia = aula.getDia();
        horario = aula.getHorario();
        turma = new TurmaDTO(aula.getTurma());
        professores = aula.getProfessores().stream().map(SimpleUserDTO::new).toList();
    }
}
