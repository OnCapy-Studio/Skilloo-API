package com.skilloo.api.controllers.professor;

import com.skilloo.api.dto.commit.CommitDTO;
import com.skilloo.api.dto.commit.CommitInsertDTO;
import com.skilloo.api.services.auth.TokenService;
import com.skilloo.api.services.professor.CommitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/minhasTurmas/{idTurma}/materias/{idMateria}/commits")
public class CommitController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CommitService commitService;

    @GetMapping
    public ResponseEntity<Page<CommitDTO>> buscarCommitsDaTurma(@PathVariable Long idTurma, @PathVariable Long idMateria, Pageable pageable){

        Long idUser = tokenService.getIdFromRequest();
        return ResponseEntity.ok(commitService.buscarCommitsDaTurma(idTurma, idMateria, idUser, pageable));
    }

    @PostMapping
    public ResponseEntity<CommitDTO> insertCommit(@RequestBody @Valid CommitInsertDTO dto, @PathVariable Long idTurma, @PathVariable Long idMateria){

        Long idUser = tokenService.getIdFromRequest();
        CommitDTO newDto = commitService.insertCommit(dto, idUser, idTurma, idMateria);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();

        return ResponseEntity.created(uri).body(newDto);
    }

    @PutMapping("/{idCommit}")
    public ResponseEntity<CommitDTO> updateCommit(@RequestBody @Valid CommitInsertDTO dto, @PathVariable Long idCommit){

        Long idUser = tokenService.getIdFromRequest();

        return ResponseEntity.ok(commitService.updateCommit(dto, idCommit, idUser));
    }

    @DeleteMapping("/{idCommit}")
    public ResponseEntity<Void> deleteCommit(@PathVariable Long idCommit){

        Long idUser = tokenService.getIdFromRequest();
        commitService.deleteCommit(idCommit, idUser);

        return ResponseEntity.noContent().build();
    }
}
