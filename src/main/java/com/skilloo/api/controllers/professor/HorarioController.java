package com.skilloo.api.controllers.professor;

import com.skilloo.api.dto.aula.AulaDTO;
import com.skilloo.api.services.AulaService;
import com.skilloo.api.services.auth.TokenService;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;

@RestController
@RequestMapping("/meuHorario")
public class HorarioController {

    @Autowired
    private AulaService aulaService;

    @Autowired
    private TokenService tokenService;

    @GetMapping("/{diaDaSemana}")
    public ResponseEntity<Page<AulaDTO>> buscarAulasPorDia(@PathVariable("diaDaSemana") DayOfWeek diaDaSemana, Pageable pageable){
        Long idUser = tokenService.getIdFromRequest();
        return ResponseEntity.ok(aulaService.buscarAulasPorDia(diaDaSemana, idUser, pageable));
    }
}
