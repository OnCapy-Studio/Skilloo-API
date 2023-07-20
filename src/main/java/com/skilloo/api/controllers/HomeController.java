package com.skilloo.api.controllers;

import com.skilloo.api.dto.aula.AulaDTO;
import com.skilloo.api.services.AulaService;
import com.skilloo.api.services.auth.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AulaService aulasService;

    @GetMapping
    public ResponseEntity<Page<AulaDTO>> buscarDadosHome(){

        Long idUser = tokenService.getIdFromRequest();

        return ResponseEntity.ok(aulasService.buscarAulasDoDia(idUser));
    }
}
