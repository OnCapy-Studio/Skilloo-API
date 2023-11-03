package com.skilloo.api.dto;

import com.skilloo.api.entities.Turma;
import com.skilloo.api.entities.enuns.Periodo;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TurmaUpdateDTO {
    private Long id;
    @NotBlank
    private String nome;
    private Periodo periodo;
    private Integer inicio;
    private Integer formacao;
    private Double avaliacao;

    public TurmaUpdateDTO(Turma entity){
        id = entity.getId();
        nome = entity.getNome();
        periodo = entity.getPeriodo();
        inicio = entity.getInicio();
        formacao = entity.getFormacao();
        avaliacao = entity.getAvaliacao();
    }
}
