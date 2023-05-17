package com.skilloo.api.dto.auth;

import com.skilloo.api.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserTokenDTO {

    private Long id;
    private String name;
    private String email;
    private String role;
    private String token;

    public UserTokenDTO(User user, String token){
        id = user.getId();
        name = user.getNome();
        email = user.getEmail();
        role = user.getRole().name();
        this.token = token;
    }

}
