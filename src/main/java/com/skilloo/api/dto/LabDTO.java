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

    public LabDTO(Lab entity){
        id = entity.getId();
        nome = entity.getNome();
    }
}
