package com.skilloo.api.dto;

import com.skilloo.api.entities.Materia;
import com.skilloo.api.entities.enuns.AreasEtec;
import com.skilloo.api.services.validation.materia.MateriaValid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@MateriaValid
public class MateriaDTO {

    private Long id;
    private String nome;
    private AreasEtec area;

    public MateriaDTO(Materia materia) {
        id = materia.getId();;
        nome = materia.getNome();
        area = materia.getArea();
    }
}
