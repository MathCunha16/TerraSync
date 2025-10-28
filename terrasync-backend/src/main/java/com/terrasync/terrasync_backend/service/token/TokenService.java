package com.terrasync.terrasync_backend.service.token;

import com.terrasync.terrasync_backend.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Component
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    private static final String ISSUER = "CEPEC API";

    public String generateToken(User user) {

        // Converte a string secreta em uma chave segura para HMAC-SHA
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        Instant now = Instant.now();
        Instant expiration = generateExpirationInstant();

        return Jwts.builder()
                .issuer(ISSUER) // Emissor
                .subject(user.getEmail()) // Assunto (email)
                .issuedAt(Date.from(now)) // Data de emissão
                .expiration(Date.from(expiration)) // Data de expiração
                .signWith(key, Jwts.SIG.HS256) // Assina com a chave e o algoritmo HS256
                .compact(); // Constrói o token como String
    }

    public String validateToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

            Claims claims = Jwts.parser()
                    .verifyWith(key) // Verifica usando a chave secreta
                    .requireIssuer(ISSUER) // Garante que o emissor é o esperado
                    .build()
                    .parseSignedClaims(token) // Faz o parse e a validação (assinatura, expiração)
                    .getPayload(); // Obtém o corpo (payload) do token

            return claims.getSubject();

        } catch (Exception e) {
            return null;
        }
    }

    private Instant generateExpirationInstant() {
        ZoneOffset goianiaOffset = ZoneOffset.of("-03:00");
        return LocalDateTime.now().plusHours(2).toInstant(goianiaOffset);
    }

}
