package com.skilloo.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skilloo.api.entities.enuns.Periodo;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "turma")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Periodo periodo;
    private Integer inicio;
    private Integer formacao;
    private Double avaliacao;

    @OneToMany(mappedBy = "turma")
    @JsonIgnore
    private List<Aula> aulas = new ArrayList<>();

    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Commit> commits = new ArrayList<>();

}
