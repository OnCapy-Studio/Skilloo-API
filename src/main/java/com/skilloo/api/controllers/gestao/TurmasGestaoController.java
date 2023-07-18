package com.skilloo.api.controllers.gestao;

import com.skilloo.api.dto.TurmaDTO;
import com.skilloo.api.services.gestao.TurmasGestaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/turmas")
public class TurmasGestaoController {

    @Autowired
    private TurmasGestaoService service;

    @GetMapping
    public ResponseEntity<Page<TurmaDTO>> findAllTurmas(Pageable pageable){
        return ResponseEntity.ok(service.findAllTurmas(pageable));
    }

    @PostMapping
    public ResponseEntity<TurmaDTO> insertTurma(@RequestBody TurmaDTO dto){
        TurmaDTO newDTO = service.insertSala(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(newDTO);
    }
}
