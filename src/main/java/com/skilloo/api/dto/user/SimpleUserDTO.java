package com.skilloo.api.dto.user;

import com.skilloo.api.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SimpleUserDTO {

    private Long id;
    private String nome;

    public SimpleUserDTO(User user){
        id = user.getId();
        nome = user.getNome();
    }
}
