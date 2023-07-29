package com.skilloo.api.controllers.professor;

import com.skilloo.api.dto.lab.LabReservaDTO;
import com.skilloo.api.services.AulaService;
import com.skilloo.api.services.auth.TokenService;
import com.skilloo.api.services.professor.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/meuHorario/{diaDaSemana}/{idAula}/reserva")
public class ReservaController {

    @Autowired
    private ReservaService service;

    @Autowired
    private AulaService aulaService;

    @Autowired
    private TokenService tokenService;

    @GetMapping
    public ResponseEntity<List<LabReservaDTO>> buscarLabs(Pageable pageable, @PathVariable Long idAula){

        return ResponseEntity.ok(service.buscarLabs(pageable, idAula));
    }

    @PostMapping("/{idLab}")
    public ResponseEntity<LabReservaDTO> reservarLab(@PathVariable Long idAula,@PathVariable Long idLab){

        //verificar em breve o tipo de retorno quando reservado(indisponivel, reservado por voce)

        Long idUser = tokenService.getIdFromRequest();
        aulaService.verificarSeProfessorEDocenteDaAula(idAula, idUser);

        LabReservaDTO dto = service.reservarLab(idAula, idLab);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getReserva().getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }


    @DeleteMapping("/{idReserva}")
    public ResponseEntity<Void> cancelarReserva(@PathVariable Long idReserva){

        Long idUser = tokenService.getIdFromRequest();

        service.cancelarReserva(idReserva, idUser);

        return ResponseEntity.noContent().build();
    }

}
