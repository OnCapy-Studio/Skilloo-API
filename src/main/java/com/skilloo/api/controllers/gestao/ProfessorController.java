package com.skilloo.api.controllers.gestao;

import com.skilloo.api.dto.user.UserDTO;
import com.skilloo.api.dto.user.UserUpdateDTO;
import com.skilloo.api.services.exceptions.DatabaseException;
import com.skilloo.api.services.gestao.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService service;

    @GetMapping
    public ResponseEntity<Page<UserDTO>> findAllProfessores(Pageable pageable){
        return ResponseEntity.ok(service.findAllProfessores(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findAllById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    //o método de inserir user está no serviço de autenticação pelas diversas regras de validação

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateProfessor(@PathVariable Long id, @RequestBody @Valid UserUpdateDTO dto){
        return ResponseEntity.ok(service.updateProfessor(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable Long id){

        try {
            service.deleteProfessor(id);
            return ResponseEntity.noContent().build();
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Erro de Integridade. Você não pode deletar um professor tendo aulas ou tickets atribuidas a ele: " + e.getMessage());
        }
    }
}
