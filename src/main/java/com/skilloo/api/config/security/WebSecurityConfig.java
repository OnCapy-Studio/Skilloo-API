package com.skilloo.api.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private AuthFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()// desabilita a segurança contra ataques, pois o proprio token ja faz isso
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//muda o processo de autenticação (não irá fornecer a tela de login e nem bloquaear as urls, isso deverá ser feito manualmente
                .and()

                // configurações para o banco h2(banco de testes)
                .authorizeHttpRequests()
                .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()//banco h2
                .and().headers().frameOptions().sameOrigin()

                .and()
                .authorizeHttpRequests()
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/users/**").hasRole("ADMIN")
                .requestMatchers("/salas/**").hasRole("ADMIN")
                .requestMatchers("/labs/**").hasRole("ADMIN")
                .requestMatchers("/turmas/**").hasRole("ADMIN")
                .requestMatchers("/home/**").hasRole("PROF")
                .requestMatchers("/minhasTurmas/**").hasRole("PROF")
                .requestMatchers("/minhasTasks/**").hasRole("PROF")
                .anyRequest().authenticated()

                .and()
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class); //adicionando o nosso filtro antes do filtro do spring

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
