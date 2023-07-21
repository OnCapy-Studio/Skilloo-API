package com.skilloo.api.repositories;

import com.skilloo.api.dto.TurmaWithMateriaDTO;
import com.skilloo.api.entities.Turma;
import com.skilloo.api.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TurmaRepository extends JpaRepository<Turma, Long> {

    @Query("SELECT t.id, t.nome, a.materia FROM Turma t " +
            "JOIN t.aulas a " +
            "WHERE a.professor = :id " +
            "GROUP BY t.nome, a.materia")
    Page<Object[]> buscarTurmasEMateriasPorProfessor(@Param("id") User user, Pageable pageable);

    Turma findByNome(String nome);
}
