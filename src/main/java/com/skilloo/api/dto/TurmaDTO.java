package com.skilloo.api.dto;

import com.skilloo.api.entities.Turma;
import com.skilloo.api.entities.enuns.Periodo;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TurmaDTO {

    private Long id;
    private String nome;
    private Periodo periodo;

    public TurmaDTO(Turma entity){
        id = entity.getId();
        nome = entity.getNome();
        periodo = entity.getPeriodo();
    }

}
