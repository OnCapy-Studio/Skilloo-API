package com.skilloo.api.dto.commit;

import com.skilloo.api.entities.Commit;
import com.skilloo.api.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class CommitDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private LocalDateTime data;
    private Long autor;

    public CommitDTO(Commit commit) {
        id = commit.getId();
        titulo = commit.getTitulo();
        descricao = commit.getDescricao();
        data = commit.getData();
        autor = commit.getAutor().getId();
    }

    public CommitDTO(Long id, String titulo, String descricao, LocalDateTime data, User autor) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.autor = autor.getId();
    }
}
