package com.skilloo.api.dto.ticketsuporte;

import com.skilloo.api.dto.user.UserDTO;
import com.skilloo.api.entities.TicketSuporte;
import com.skilloo.api.entities.enuns.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketSuporteDTO {

    private Long id;
    private String titulo;
    private String lab;
    private String descricao;
    private String status;
    private UserDTO professor;

    public TicketSuporteDTO(TicketSuporte entity){
        id = entity.getId();
        titulo = entity.getTitulo();
        lab = entity.getLab();
        descricao = entity.getDescricao();
        status = entity.getStatus().toString();
        professor = new UserDTO(entity.getProfessor());
    }
}
