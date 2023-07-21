package com.skilloo.api.dto.user.admin;

import com.skilloo.api.services.validation.user.UserAdminInsertValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@UserAdminInsertValid
public class UserAdminInsertDTO {

    @Size(min = 5, max = 60, message = "Deve ter entre 5 e 60 caracteres")
    @NotBlank(message = "Campo Requirido")
    private String nome;

    @Email(message = "Insira um email valido")
    private String email;
    @NotBlank
    @Size(min = 6, max = 60, message = "Deve ter entre 6 e 60 caracteres")
    private String senha;
    @NotNull(message = "Campo Requirido")
    private String descricao;

}
