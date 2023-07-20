package com.skilloo.api.dto.lab;

import com.skilloo.api.dto.aula.AulaDTO;
import com.skilloo.api.entities.Aula;
import com.skilloo.api.entities.Lab;
import com.skilloo.api.entities.Reserva;
import com.skilloo.api.entities.enuns.LabStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;

@NoArgsConstructor
@Getter
@Setter
public class LabReservaDTO {

    private Long labId;
    private String nome;
    private LabStatus status;
    private ReservaDTO reserva;

    public LabReservaDTO(Lab lab){
        labId = lab.getId();
        nome = lab.getNome();
        status = LabStatus.DISPONIVEL;
    }

    public LabReservaDTO(Long id, String nomeLab, Reserva reserva, DayOfWeek dia, LocalTime horario) {
        labId = id;
        nome = nomeLab;
        status = LabStatus.INDISPONIVEL;
        this.reserva = new ReservaDTO(reserva);
    }

    public LabReservaDTO(Lab lab, Reserva reserva){
        labId = lab.getId();
        nome = lab.getNome();
        status = LabStatus.INDISPONIVEL;
        this.reserva = new ReservaDTO(reserva);
    }
}
