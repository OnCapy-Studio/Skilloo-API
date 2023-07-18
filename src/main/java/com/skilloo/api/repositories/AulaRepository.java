package com.skilloo.api.repositories;

import com.skilloo.api.entities.Aula;
import com.skilloo.api.entities.Turma;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AulaRepository extends JpaRepository<Aula, Long> {


    List<Aula> findAllByProfessorId(Long id);

    void deleteByProfessorId(Long idUser);


    //query para buscar os id's das turmas de um professor
    @Query(value = "SELECT turma FROM aula WHERE aula.professor = :id GROUP BY aula.turma", nativeQuery = true)
    Page<Long> buscarTurmasPorProfessor(@Param("id") Long id, Pageable pageable);


    @Query(value = "SELECT turma, materia FROM aula WHERE professor = :idUser AND turma = :idTurma AND materia = :idMateria GROUP BY turma, materia", nativeQuery = true)
    List<Object[]> verificarSeProfessorTemAulaComUmaTurma(@Param("idUser")Long idUser, @Param("idTurma")Long idTurma, @Param("idMateria")Long idMateria);
}
