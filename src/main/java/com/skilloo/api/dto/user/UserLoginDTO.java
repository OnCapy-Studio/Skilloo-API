package com.skilloo.api.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserLoginDTO {

    @Email(message = "Insira um email válido")
    private String email;

    @NotBlank(message = "Campo Obrigatório")
    private String senha;
}
