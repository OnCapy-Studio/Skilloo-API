package com.skilloo.api.entities;

import com.skilloo.api.entities.enuns.StatusTask;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private LocalDateTime prazo;
    private String anotacao;
    @Enumerated(EnumType.STRING)
    private StatusTask status;

    @ManyToOne
    @JoinColumn(name = "autor")
    private User autor;
}
