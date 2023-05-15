package com.skilloo.api.dto.user;

import com.skilloo.api.entities.enuns.AreasEtec;
import com.skilloo.api.entities.enuns.Contrato;
import com.skilloo.api.entities.enuns.Role;
import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserUpdateDTO {

    @Size(min = 5, max = 60, message = "Deve ter entre 5 e 60 caracteres")
    @NotBlank(message = "Campo Requirido")
    private String nome;
    @Email(message = "Insira um email valido")
    private String email;

    @NotBlank
    @Size(min = 6, max = 60, message = "Deve ter entre 6 e 60 caracteres")
    private String senha;

    @NotNull(message = "Campo Requirido")
    private Role role;

    @NotNull(message = "Campo Requirido")
    private AreasEtec area;

    @Min(1)
    private Double pontuacao;

    @NotNull(message = "Campo Requirido")
    private Contrato contrato;
}
