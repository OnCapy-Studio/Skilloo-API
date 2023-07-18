package com.skilloo.api.dto;

import com.skilloo.api.entities.Materia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MateriaDTO {

    private Long id;
    private String nome;

    public MateriaDTO(Materia materia) {
        id = materia.getId();;
        nome = materia.getNome();
    }
}
