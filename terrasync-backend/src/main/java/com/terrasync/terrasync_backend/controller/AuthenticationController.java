package com.terrasync.terrasync_backend.controller;

import com.terrasync.terrasync_backend.dto.auth.LoginRequestDTO;
import com.terrasync.terrasync_backend.dto.auth.LoginResponseDTO;
import com.terrasync.terrasync_backend.entity.User;
import com.terrasync.terrasync_backend.service.token.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO data) {
        // 1. Cria o objeto de autenticação com email e senha
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.hashedPassword());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        var user = (User) auth.getPrincipal();

        var token = tokenService.generateToken(user);

        // 5. Retorna o token em um DTO de resposta
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

}
