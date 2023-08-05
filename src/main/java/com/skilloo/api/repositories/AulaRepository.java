package com.skilloo.api.repositories;

import com.skilloo.api.entities.Aula;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.DayOfWeek;
import java.util.List;

public interface AulaRepository extends JpaRepository<Aula, Long> {


    @Query(value = "SELECT a FROM Aula a " +
            "JOIN a.professores p " +
            "WHERE p.id = :id")
    List<Aula> findAllByProfessorId(@Param("id")Long id);


    //query para buscar os id's das turmas de um professor
    /*@Query(value = "SELECT turma FROM aula WHERE aula.professor = :id GROUP BY aula.turma", nativeQuery = true)
    Page<Long> buscarTurmasPorProfessor(@Param("id") Long id, Pageable pageable);*/


    @Query(value = "SELECT turma, materia FROM aula " +
            "JOIN aula_professores ON aula.id = aula_professores.aula_id " +
            "WHERE aula_professores.professor_id = :idUser AND turma = :idTurma AND materia = :idMateria GROUP BY turma, materia", nativeQuery = true)
    List<Object[]> verificarSeProfessorTemAulaComUmaTurma(@Param("idUser")Long idUser, @Param("idTurma")Long idTurma, @Param("idMateria")Long idMateria);

    @Query(value = "SELECT a FROM Aula a " +
            "JOIN a.professores p " +
            "WHERE a.dia = :diaDaSemana AND p.id = :idUser")
    Page<Aula> buscarAulasPorDia(@Param("diaDaSemana") DayOfWeek diaDaSemana, @Param("idUser")Long idUser, Pageable pageable);

    @Query(value = "SELECT a FROM Aula a " +
            "JOIN a.turma t " +
            "WHERE t.id = :idTurma AND a.dia = :dia")
    Page<Aula> buscarAulasPorTurma(@Param("idTurma") Long idTurma , DayOfWeek dia, Pageable pageable);

    @Query(value = "SELECT a FROM Aula a " +
            "JOIN a.turma t " +
            "WHERE t.id = :idTurma AND a.dia = :dia")
    List<Aula> buscarAulasPorTurma(@Param("idTurma") Long idTurma , DayOfWeek dia);
}
