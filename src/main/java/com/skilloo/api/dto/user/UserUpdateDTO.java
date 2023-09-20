package com.skilloo.api.dto.user;

import com.skilloo.api.entities.enuns.AreasEtec;
import com.skilloo.api.entities.enuns.Contrato;
import com.skilloo.api.entities.enuns.Role;
import com.skilloo.api.services.validation.user.UserUpdateValid;
import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@UserUpdateValid
public class UserUpdateDTO {

    @Size(min = 5, max = 60, message = "Deve ter entre 5 e 60 caracteres")
    @NotBlank(message = "Campo Requirido")
    private String nome;
    @Email(message = "Insira um email valido")
    private String email;


    @NotNull(message = "Campo Requirido")
    private AreasEtec area;

    @Min(1)
    private Double pontuacao;

    @NotNull(message = "Campo Requirido")
    private Contrato contrato;
}
