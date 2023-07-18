package com.skilloo.api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "commit")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Commit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "turma")
    private Turma turma;

    @ManyToOne
    @JoinColumn(name = "materia")
    private Materia materia;

    @ManyToOne
    @JoinColumn(name = "autor")
    private User autor;
}
