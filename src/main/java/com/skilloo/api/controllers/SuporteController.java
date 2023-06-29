package com.skilloo.api.controllers;

import com.skilloo.api.dto.ticketsuporte.TicketSuporteDTO;
import com.skilloo.api.dto.ticketsuporte.TicketSuporteInsertDTO;
import com.skilloo.api.services.SuporteService;
import com.skilloo.api.services.auth.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/suporte")
public class SuporteController {

    @Autowired
    private SuporteService service;

    @Autowired
    private TokenService tokenService;


    @GetMapping
    public ResponseEntity<Page<TicketSuporteDTO>> findAllTickets(Pageable pageable){

        Page<TicketSuporteDTO> tickets = service.findAllTickets(pageable);

        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/meusTickets")
    public ResponseEntity<Page<TicketSuporteDTO>> findTicketsByProfessor(Pageable pageable){


        Long idUser = tokenService.getIdFromRequest();
        Page<TicketSuporteDTO> tickets = service.findTicketsByProfessor(idUser, pageable);

        return ResponseEntity.ok(tickets);
    }

    @PostMapping
    public ResponseEntity<TicketSuporteDTO> createTicket(@RequestBody @Valid TicketSuporteInsertDTO dto){

        Long idUser = tokenService.getIdFromRequest();
        TicketSuporteDTO ticketSuporteDTO = service.createTicket(dto, idUser);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(ticketSuporteDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(ticketSuporteDTO);
    }

    @PutMapping("/{idTicket}")
    public ResponseEntity<TicketSuporteDTO> updateTicket(@PathVariable Long idTicket, @RequestBody @Valid TicketSuporteInsertDTO dto){

        Long idUser = tokenService.getIdFromRequest();
        TicketSuporteDTO ticketSuporteDTO = service.updateTicket(idTicket, dto, idUser);

        return ResponseEntity.ok(ticketSuporteDTO);
    }

    @DeleteMapping("/{idTicket}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long idTicket){

        Long idUser = tokenService.getIdFromRequest();
        service.deleteTicket(idTicket, idUser);

        return ResponseEntity.noContent().build();
    }

}
