package com.skilloo.api.repositories;

import com.skilloo.api.dto.MateriaDTO;
import com.skilloo.api.entities.Materia;
import com.skilloo.api.entities.enuns.AreasEtec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MateriaRepository extends JpaRepository<Materia, Long> {
    @Query(value = "SELECT m FROM Materia m " +
            "WHERE m.area = :area")
    Page<MateriaDTO> buscarMateriasPorArea(@Param("area") AreasEtec area , Pageable pageable);

    Materia findByNome(String nome);
}
