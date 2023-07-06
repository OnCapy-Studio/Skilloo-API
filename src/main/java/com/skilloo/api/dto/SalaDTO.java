package com.skilloo.api.dto;

import com.skilloo.api.entities.Sala;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SalaDTO {

    private Long id;
    @NotBlank
    private String nome;

    public SalaDTO(Sala entity){
        id = entity.getId();
        nome = entity.getNome();
    }
}
