package com.skilloo.api.dto;

import com.skilloo.api.entities.Turma;
import com.skilloo.api.entities.enuns.Periodo;
import com.skilloo.api.services.validation.turma.TurmaValid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@TurmaValid
public class TurmaDTO {

    private Long id;
    @NotBlank
    private String nome;
    private Periodo periodo;
    private Integer inicio;
    private Integer formacao;
    private Double avaliacao;

    public TurmaDTO(Turma entity){
        id = entity.getId();
        nome = entity.getNome();
        periodo = entity.getPeriodo();
        inicio = entity.getInicio();
        formacao = entity.getFormacao();
        avaliacao = entity.getAvaliacao();
    }

}
