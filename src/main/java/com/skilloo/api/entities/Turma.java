package com.skilloo.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skilloo.api.entities.enuns.Periodo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "turma")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Periodo periodo;

    @OneToMany(mappedBy = "turma")
    @JsonIgnore
    private List<Aula> aulas = new ArrayList<>();
}