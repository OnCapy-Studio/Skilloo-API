package com.skilloo.api.dto.ticketsuporte;

import com.skilloo.api.dto.user.UserDTO;
import com.skilloo.api.entities.TicketSuporte;
import com.skilloo.api.entities.enuns.TicketStatus;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketSuporteInsertDTO {

    @NotBlank
    private String titulo;
    @NotBlank
    private String lab;
    @NotBlank
    private String descricao;

    @Enumerated
    private TicketStatus status;
}
