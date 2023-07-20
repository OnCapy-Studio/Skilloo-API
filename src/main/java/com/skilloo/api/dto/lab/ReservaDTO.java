package com.skilloo.api.dto.lab;

import com.skilloo.api.dto.aula.AulaDTO;
import com.skilloo.api.entities.Reserva;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservaDTO {

    private Long id;
    private AulaDTO aulaDTO;

    public ReservaDTO(Reserva reserva) {
        id = reserva.getId();
        aulaDTO = new AulaDTO(reserva.getAula());
    }
}
