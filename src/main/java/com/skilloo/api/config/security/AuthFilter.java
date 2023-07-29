package com.skilloo.api.config.security;

import com.skilloo.api.repositories.ProfessorRepository;
import com.skilloo.api.services.auth.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ProfessorRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

           //buscando o token da request
           var token = recuperarToken(request);

           //caso volte um token
           if (token != null) {
               //busque o email do user no token
               String subject = tokenService.getSubject(token);

               //busque o usuário pelo subject(email)
               var user = userRepository.findByEmail(subject);


               // confirmando a autenticação
               var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()); //criando as credenciais
               SecurityContextHolder.getContext().setAuthentication(authentication);

           }
            filterChain.doFilter(request, response);
    }

    //método privado para recuperar um token de uma request
    private String recuperarToken(HttpServletRequest request) {

        var authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null){
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}
