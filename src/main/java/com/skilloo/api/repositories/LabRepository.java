package com.skilloo.api.repositories;

import com.skilloo.api.entities.Lab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public interface LabRepository extends JpaRepository<Lab, Long> {

    @Query(value = "SELECT * FROM lab JOIN reserva ON reserva.lab = lab.id WHERE reserva.dia = :#{#dia.name()} AND reserva.horario = :horario", nativeQuery = true)
    List<Object[]> buscarLabsComReserva(@Param("dia") DayOfWeek dia, @Param("horario") LocalTime horario);
}
