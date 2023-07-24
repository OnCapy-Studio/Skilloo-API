package com.skilloo.api.controllers.gestao;

import com.skilloo.api.dto.aula.AulaDTO;
import com.skilloo.api.dto.aula.AulaInsertDTO;
import com.skilloo.api.services.AulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;

@RestController
@RequestMapping("/horario")
public class HorarioGestaoController {

    @Autowired
    private AulaService aulaService;

    @GetMapping("/{idTurma}/{dia}")
    public ResponseEntity<Page<AulaDTO>> buscarAulasPorTurma(@PathVariable Long idTurma, @PathVariable DayOfWeek dia, Pageable pageable){

        return ResponseEntity.ok(aulaService.buscarAulasPorTurma(idTurma, dia, pageable));
    }

    @PutMapping("/{idTurma}/{dia}")
    public ResponseEntity<Void> atualizarHorario(@PathVariable Long idTurma, @PathVariable DayOfWeek dia, @RequestBody List<AulaInsertDTO> dtos){
        aulaService.atualizarHorario(idTurma, dia, dtos);
        return ResponseEntity.ok().build();
    }

    
}
