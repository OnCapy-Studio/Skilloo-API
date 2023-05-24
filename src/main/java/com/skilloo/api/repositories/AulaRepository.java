package com.skilloo.api.repositories;

import com.skilloo.api.entities.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AulaRepository extends JpaRepository<Aula, Long> {


    List<Aula> findAllByProfessorId(Long id);

    void deleteByProfessorId(Long idUser);
}
