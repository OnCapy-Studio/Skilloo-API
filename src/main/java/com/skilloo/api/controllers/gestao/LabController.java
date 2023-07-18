package com.skilloo.api.controllers.gestao;

import com.skilloo.api.dto.LabDTO;
import com.skilloo.api.services.LabService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/labs")
public class LabController {

    @Autowired
    private LabService service;

    @GetMapping
    public ResponseEntity<Page<LabDTO>> findAllLabs(Pageable pageable){
        return ResponseEntity.ok(service.findAllLabs(pageable));
    }

    @PostMapping
    public ResponseEntity<LabDTO> insertLab(@RequestBody @Valid LabDTO dto){

        LabDTO newDto = service.insertLab(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();

        return ResponseEntity.created(uri).body(newDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LabDTO> updateLab(@PathVariable Long id, @RequestBody @Valid LabDTO dto){
        return ResponseEntity.ok(service.updateLab(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLab(@PathVariable Long id){
        service.deleteLab(id);
        return ResponseEntity.noContent().build();
    }
}
