package com.skilloo.api.dto;

import com.skilloo.api.entities.Lab;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LabDTO {

    private Long id;
    @NotBlank
    private String nome;
    private Integer capacidade;
    private Integer maquinas;
    private String descricao;

    public LabDTO(Lab entity){
        id = entity.getId();
        nome = entity.getNome();
        capacidade = entity.getCapacidade();
        maquinas = entity.getMaquinas();
        descricao = entity.getDescricao();
    }
}
