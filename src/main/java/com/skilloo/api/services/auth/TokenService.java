package com.skilloo.api.services.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.skilloo.api.entities.User;
import com.skilloo.api.services.exceptions.TokenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Value("${api.security.token.secret")
    private String secret;

    //método para gerar token
    public String gerarToken(User user){
        try {
            //definindo o algoritmo dono do token
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("skilloo-api")//assinatura da api
                    .withSubject(user.getEmail())// a quem pertence o token
                    .withClaim("Id", user.getId()) // id do dono do token
                    .sign(algorithm);// atribuindo o algoritmo ao token

        } catch (JWTCreationException exception){
            throw new TokenException("Não foi possivel gerar o token");
        }
    }

    //método para verificar se um token é valido, e retornar o email do usuário
    public String getSubject(String token) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("skilloo-api")
                    .build()
                    .verify(token)// verificar se o token é valido de acordo com o algoritmo e com a issuer
                    .getSubject();// caso seja valido, retorne o subject(email);
        }

        catch (JWTVerificationException e){
            throw new TokenException("Token JWT inválido ou expirado");
        }
    }

    //método para verificar se um token é valido, e retornar o id do usuário
    public Long getId(String token) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("skilloo-api")
                    .build()
                    .verify(token)// verificar se o token é valido de acordo com o algoritmo e com a issuer
                    .getClaim("Id").asLong();// caso seja valido, retorne o id;
        }

        catch (JWTVerificationException e){
            throw new TokenException("Token JWT inválido ou expirado");
        }
    }
}
