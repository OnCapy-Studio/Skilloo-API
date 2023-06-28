package com.skilloo.api.controllers;

import com.skilloo.api.dto.user.UserDTO;
import com.skilloo.api.dto.user.UserInsertDTO;
import com.skilloo.api.dto.user.UserUpdateDTO;
import com.skilloo.api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/professores")
public class UserController {

    @Autowired
    private UserService service;

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
        service.deleteProfessor(id);
        return ResponseEntity.noContent().build();
    }
}
