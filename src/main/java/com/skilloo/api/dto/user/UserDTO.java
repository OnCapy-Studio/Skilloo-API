package com.skilloo.api.dto.user;

import com.skilloo.api.entities.User;
import com.skilloo.api.entities.enuns.AreasEtec;
import com.skilloo.api.entities.enuns.Contrato;
import com.skilloo.api.entities.enuns.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private Long id;
    private String nome;
    private String email;
    private AreasEtec area;
    private Double pontuacao;
    private Contrato contrato;

    public UserDTO(User user){
        id = user.getId();
        nome = user.getNome();
        email = user.getEmail();
        area = user.getArea();
        pontuacao = user.getPontuacao();
        contrato = user.getContrato();
    }
}
