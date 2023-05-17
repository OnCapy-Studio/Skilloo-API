package com.skilloo.api.controllers;

import com.skilloo.api.services.auth.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private TokenService tokenService;

    @GetMapping
    public ResponseEntity buscarDadosHome(){

        String token = request.getHeader("Authorization").replace("Bearer ", "");

        return ResponseEntity.ok(tokenService.getId(token));
    }
}
