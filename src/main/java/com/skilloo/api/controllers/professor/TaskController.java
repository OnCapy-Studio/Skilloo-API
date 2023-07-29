package com.skilloo.api.controllers.professor;

import com.skilloo.api.dto.TaskDTO;
import com.skilloo.api.services.auth.TokenService;
import com.skilloo.api.services.professor.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/minhasTasks")
public class TaskController {

    @Autowired
    private TaskService service;

    @Autowired
    private TokenService tokenService;

    @GetMapping
    public ResponseEntity<Page<TaskDTO>> findAllTasks(Pageable pageable){

        Long idUser = tokenService.getIdFromRequest();
        return ResponseEntity.ok(service.findAllTasks(pageable, idUser));
    }

    @PostMapping
    public ResponseEntity<TaskDTO> insertTask(@RequestBody @Valid TaskDTO dto){

        Long idUser = tokenService.getIdFromRequest();
        TaskDTO newDTO = service.insertTask(dto, idUser);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{idTask}")
    public ResponseEntity<TaskDTO> updateTask(@RequestBody @Valid TaskDTO dto, @PathVariable Long idTask){

        Long idUser = tokenService.getIdFromRequest();
        return ResponseEntity.ok(service.updateTask(dto, idTask, idUser));
    }

    @DeleteMapping("/{idTask}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long idTask){

        Long idUser = tokenService.getIdFromRequest();
        service.deleteTask(idTask, idUser);

        return ResponseEntity.noContent().build();
    }
}
