package com.terrasync.backend.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Desativa a proteção CSRF - Essencial para APIs REST
                .csrf(csrf -> csrf.disable())

                // 2. Configura as regras de autorização
                .authorizeHttpRequests(auth -> auth
                        // Por agora, vamos permitir todos os pedidos para facilitar os testes.
                        // Mais tarde, vamos configurar regras específicas aqui.
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}
