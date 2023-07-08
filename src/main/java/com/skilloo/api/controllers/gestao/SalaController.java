package com.skilloo.api.controllers.gestao;

import com.skilloo.api.dto.SalaDTO;
import com.skilloo.api.services.SalaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/salas")
public class SalaController {

    @Autowired
    private SalaService service;

    @GetMapping
    public ResponseEntity<Page<SalaDTO>> findAllSalas(Pageable pageable){

        return ResponseEntity.ok(service.findAllSalas(pageable));
    }

    @PostMapping
    public ResponseEntity<SalaDTO> insertSala(@RequestBody @Valid SalaDTO dto){
        SalaDTO newDTO = service.insertSala(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(newDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalaDTO> updateSala(@PathVariable Long id, @RequestBody @Valid SalaDTO dto){

        return ResponseEntity.ok(service.updateSala(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSala(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
