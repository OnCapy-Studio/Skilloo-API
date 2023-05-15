package com.skilloo.api.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

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
                .authorizeHttpRequests().anyRequest().permitAll();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
