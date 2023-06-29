package com.skilloo.api.repositories;

import com.skilloo.api.entities.TicketSuporte;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketSuporteRepository extends JpaRepository<TicketSuporte, Long> {

    Page<TicketSuporte> findAllByProfessorId(Long id, Pageable pageable);
}
