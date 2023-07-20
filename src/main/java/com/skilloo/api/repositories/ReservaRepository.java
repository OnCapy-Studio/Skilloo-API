package com.skilloo.api.repositories;

import com.skilloo.api.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query(value = "SELECT * FROM reserva JOIN lab ON reserva.lab = lab.id WHERE lab.id = :idLab AND reserva.dia = :#{#dia.name()} AND reserva.horario = :horario", nativeQuery = true)
    List<Object[]> verificarSeJaExisteReserva(@Param("idLab") Long idLab, @Param("dia") DayOfWeek dia, @Param("horario") LocalTime horario);
}
