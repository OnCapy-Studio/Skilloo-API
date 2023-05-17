package com.skilloo.api.controllers.auth;

import com.skilloo.api.dto.auth.UserTokenDTO;
import com.skilloo.api.dto.user.UserDTO;
import com.skilloo.api.dto.user.UserInsertDTO;
import com.skilloo.api.dto.user.admin.UserAdminInsertDTO;
import com.skilloo.api.dto.user.UserLoginDTO;
import com.skilloo.api.services.auth.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    //rota para cadastro apenas dos coordenadores
    @PostMapping("/signup/admin")
    public ResponseEntity<UserTokenDTO> signupAdmin(@RequestBody @Valid UserAdminInsertDTO dto){

        UserTokenDTO newDto = authService.signupAdmin(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();

        return ResponseEntity.created(uri).body(newDto);
    }

    //rota para cadastro dos professores
    @PostMapping("/signup")
    public ResponseEntity<UserTokenDTO> signup(@RequestBody @Valid UserInsertDTO dto){

        UserTokenDTO newDto = authService.signup(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();

        return ResponseEntity.created(uri).body(newDto);
    }

    //rota de login para ambos (ADMIN, PROF)
    @PostMapping("/login")
    public ResponseEntity<UserTokenDTO> login(@RequestBody @Valid UserLoginDTO dto){
        return ResponseEntity.ok(authService.login(dto));
    }
}
