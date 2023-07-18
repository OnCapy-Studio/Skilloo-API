package com.skilloo.api.entities;

import com.skilloo.api.entities.enuns.Dia;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.repository.query.Param;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name = "aula")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private DayOfWeek dia;
    private LocalTime horario;

    @ManyToOne
    @JoinColumn(name = "professor")
    private User professor;

    @ManyToOne
    @JoinColumn(name = "turma")
    private Turma turma;


    @ManyToOne
    @JoinColumn(name = "materia")
    private Materia materia;
}
