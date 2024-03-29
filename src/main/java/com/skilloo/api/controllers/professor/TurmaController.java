package com.skilloo.api.controllers.professor;

import com.skilloo.api.dto.TurmaWithMateriaDTO;
import com.skilloo.api.services.auth.TokenService;
import com.skilloo.api.services.professor.CommitService;
import com.skilloo.api.services.professor.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/minhasTurmas")
public class TurmaController {

    @Autowired
    private TurmaService service;

    @Autowired
    private CommitService commitService;

    @Autowired
    private TokenService tokenService;


    @GetMapping
    public ResponseEntity<Page<TurmaWithMateriaDTO>> findAllTurmas(Pageable pageable){

        Long idUser = tokenService.getIdFromRequest();
        return ResponseEntity.ok(service.findAllTurmas(pageable, idUser));
    }
}
