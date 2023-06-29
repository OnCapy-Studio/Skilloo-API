package com.skilloo.api.services.auth;

import com.skilloo.api.dto.auth.UserTokenDTO;
import com.skilloo.api.dto.user.UserInsertDTO;
import com.skilloo.api.dto.user.admin.UserAdminInsertDTO;
import com.skilloo.api.dto.user.UserLoginDTO;
import com.skilloo.api.entities.User;
import com.skilloo.api.entities.enuns.Role;
import com.skilloo.api.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProfessorRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    //cadastro de admin
    public UserTokenDTO signupAdmin(UserAdminInsertDTO dto){
        //criando um novo user a partir do dto
        User user = new User();
        user.setNome(dto.getNome());
        user.setEmail(dto.getEmail());
        user.setSenha(passwordEncoder.encode(dto.getSenha()));
        user.setRole(Role.ADMIN);
        user.setDescricao(dto.getDescricao());
        user.setArea(null);
        user.setContrato(null);
        user.setPontuacao(null);

        //salvando no repositório
        user = userRepository.save(user);

        //gerando o token a partir do usuario
        var tokenJWT = tokenService.gerarToken(user);

        return new UserTokenDTO(user, tokenJWT);
    }

    //cadastro de prof
    public UserTokenDTO signup(UserInsertDTO dto) {
        //criando um novo user a partir do dto
        User user = new User();
        user.setNome(dto.getNome());
        user.setEmail(dto.getEmail());
        user.setSenha(passwordEncoder.encode(dto.getSenha()));
        user.setRole(Role.PROF);
        user.setDescricao(dto.getDescricao());
        user.setArea(dto.getArea());
        user.setContrato(dto.getContrato());
        user.setPontuacao(dto.getPontuacao());

        //salvando o user no repositório
        user = userRepository.save(user);

        //gerando um token a partir do user
        var tokenJWT = tokenService.gerarToken(user);

        return new UserTokenDTO(user, tokenJWT);
    }

    //login para ADMIN, PROF
    public UserTokenDTO login(UserLoginDTO dto) {

        //gerando as credenciais
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha());

        //autenticando com o sistema
        var authentication = authenticationManager.authenticate(authenticationToken);

        //gerando o token a partir do user devolvido pela autenticação
        var user = (User) authentication.getPrincipal();
        var tokenJWT = tokenService.gerarToken(user);

        return new UserTokenDTO(user, tokenJWT);
    }


}
