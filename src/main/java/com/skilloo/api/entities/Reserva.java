package com.skilloo.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name = "reserva")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalTime horario;
    @Enumerated(EnumType.STRING)
    private DayOfWeek dia;

    @OneToOne
    @JoinColumn(name="aula")
    private Aula aula;

    @ManyToOne
    @JoinColumn(name = "lab")
    private Lab lab;
}
