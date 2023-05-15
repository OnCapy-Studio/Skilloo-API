package com.skilloo.api.dto.user;

import com.skilloo.api.entities.enuns.AreasEtec;
import com.skilloo.api.entities.enuns.Contrato;
import com.skilloo.api.entities.enuns.Role;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserInsertDTO {


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
