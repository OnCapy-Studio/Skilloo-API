package com.skilloo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TurmaWithMateriaDTO {

    private Long idTurma;
    private String nomeTurma;
    private List<MateriaDTO> materias = new ArrayList<>();


}
