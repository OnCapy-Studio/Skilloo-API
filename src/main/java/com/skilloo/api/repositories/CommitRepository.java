package com.skilloo.api.repositories;

import com.skilloo.api.dto.commit.CommitDTO;
import com.skilloo.api.entities.Commit;
import com.skilloo.api.entities.Materia;
import com.skilloo.api.entities.Turma;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommitRepository extends JpaRepository<Commit, Long> {

    @Query("SELECT new com.skilloo.api.dto.commit.CommitDTO(c.id, c.titulo, c.descricao, c.data, c.autor) FROM Commit c " +
            "JOIN c.turma t JOIN c.materia m " +
            "WHERE t.id = :turma AND m.id = :materia")
    Page<CommitDTO> buscarCommitsDaTurma(@Param("turma") Long turma, @Param("materia") Long materia, Pageable pageable);
}
