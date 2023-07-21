package com.skilloo.api.controllers.gestao;

import com.skilloo.api.dto.aula.AulaDTO;
import com.skilloo.api.services.AulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/horario")
public class HorarioGestaoController {

    @Autowired
    private AulaService aulaService;

    @GetMapping("/{idTurma}")
    public ResponseEntity<Page<AulaDTO>> buscarAulasPorTurma(@PathVariable Long idTurma, Pageable pageable){

        return ResponseEntity.ok(aulaService.buscarAulasPorTurma(idTurma, pageable));
    }

    
}
