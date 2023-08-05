package com.skilloo.api.entities;

import com.skilloo.api.entities.enuns.Dia;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.repository.query.Param;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "aula")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private DayOfWeek dia;
    private LocalTime horario;

    @ManyToMany
    @JoinTable(
            name = "aula_professores",
            joinColumns = @JoinColumn(name = "aula_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id"))
    private List<User> professores = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "turma")
    private Turma turma;


    @ManyToOne
    @JoinColumn(name = "materia")
    private Materia materia;

    @OneToOne(mappedBy = "aula")
    private Reserva reserva;
}
