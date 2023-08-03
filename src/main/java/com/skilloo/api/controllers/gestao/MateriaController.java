package com.skilloo.api.controllers.gestao;

import com.skilloo.api.dto.MateriaDTO;
import com.skilloo.api.entities.enuns.AreasEtec;
import com.skilloo.api.services.exceptions.DatabaseException;
import com.skilloo.api.services.gestao.MateriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/materias")
public class MateriaController {

    @Autowired
    private MateriaService service;

    @GetMapping
    public ResponseEntity<Page<MateriaDTO>> findAllMaterias(Pageable pageable){
        return ResponseEntity.ok(service.findAllMaterias(pageable));
    }

    @GetMapping("/{area}")
    public ResponseEntity<Page<MateriaDTO>> buscarMateriasPorArea(@PathVariable AreasEtec area, Pageable pageable){
        return ResponseEntity.ok(service.buscarMateriasPorArea(area, pageable));
    }

    @PostMapping
    public ResponseEntity<MateriaDTO> insertMateria(@RequestBody @Valid MateriaDTO dto){

        MateriaDTO newDto = service.insertMateria(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();

        return ResponseEntity.created(uri).body(newDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MateriaDTO> updateMateria(@PathVariable Long id, @RequestBody MateriaDTO dto){
        return ResponseEntity.ok(service.updateMateria(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMateria(@PathVariable Long id){

        try{
            service.deleteMateria(id);
            return ResponseEntity.noContent().build();
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Erro de Integridade. Você não pode deletar uma matéria tendo aulas, atribuidas a ela: " + e.getMessage());
        }
    }
}
