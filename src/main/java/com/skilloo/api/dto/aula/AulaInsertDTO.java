package com.skilloo.api.dto.aula;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AulaInsertDTO {

    private LocalTime horario;
    private Long materiaId;
    private Long professorId;
}
