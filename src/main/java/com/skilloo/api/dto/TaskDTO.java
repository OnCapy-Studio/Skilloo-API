package com.skilloo.api.dto;

import com.skilloo.api.entities.Task;
import com.skilloo.api.entities.enuns.StatusTask;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskDTO {

    private Long id;
    @NotBlank
    private String titulo;
    @FutureOrPresent
    private LocalDateTime prazo;
    @NotBlank
    private String anotacao;
    private StatusTask status;

    public TaskDTO(Task task) {
        id = task.getId();
        titulo = task.getTitulo();
        prazo = task.getPrazo();
        anotacao = task.getAnotacao();
        status = task.getStatus();
    }
}
