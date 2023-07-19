package com.skilloo.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skilloo.api.entities.enuns.AreasEtec;
import com.skilloo.api.entities.enuns.Contrato;
import com.skilloo.api.entities.enuns.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tb_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Column(unique = true)
    private String email;
    private String senha;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private AreasEtec area;
    private Double pontuacao;
    private String descricao;

    @Enumerated(EnumType.STRING)
    private Contrato contrato;

    //relações
    @OneToMany(mappedBy = "professor")
    private List<Aula> aulas = new ArrayList<>();

    @OneToMany(mappedBy = "professor")
    private List<TicketSuporte> tickets = new ArrayList<>();

    @OneToMany(mappedBy = "autor")
    private List<Task> tasks = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>();

        if(this.role.equals(Role.PROF)){
            authorities.add(new SimpleGrantedAuthority("ROLE_PROF"));
        }

        if(this.role.equals(Role.ADMIN)){
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
