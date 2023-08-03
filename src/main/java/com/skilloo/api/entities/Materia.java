package com.skilloo.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skilloo.api.entities.enuns.AreasEtec;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "materia")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private AreasEtec area;

    @OneToMany(mappedBy = "materia")
    @JsonIgnore
    private List<Aula> aulas = new ArrayList<>();

    @OneToMany(mappedBy = "materia", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Commit> commits = new ArrayList<>();
}
