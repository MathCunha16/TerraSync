package com.terrasync.backend.unit.dto;

import com.terrasync.backend.dto.cropType.CropTypeRequestDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CropTypeRequestDTO - Validation Test")
class CropTypeRequestDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("Deve criar DTO quando nome válido")
    void deveCriarDTO_QuandoNomeValido() {
        String nomeValido = "Milho";

        CropTypeRequestDTO dto = new CropTypeRequestDTO(nomeValido);
        Set<ConstraintViolation<CropTypeRequestDTO>> violations = validator.validate(dto);

        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("Não deve criar DTO quando nome vazio")
    void naoDeveCriarDTO_QuandoNomeVazio() {
        String nomeVazio = "";

        CropTypeRequestDTO dto = new CropTypeRequestDTO(nomeVazio);
        Set<ConstraintViolation<CropTypeRequestDTO>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());

        String mensagem = violations.iterator().next().getMessage();
        assertEquals("Name is required", mensagem);
    }

    @Test
    @DisplayName("Não deve criar DTO quando nome nulo")
    void naoDeveCriarDTO_QuandoNomeNulo() {
        String nomeNulo = null;

        CropTypeRequestDTO dto = new CropTypeRequestDTO(nomeNulo);
        Set<ConstraintViolation<CropTypeRequestDTO>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
    }

    @Test
    @DisplayName("Não deve criar DTO quando nome muito curto")
    void naoDeveCriarDTO_QuandoNomeMuitoCurto() {
        String nomeCurto = "A";

        CropTypeRequestDTO dto = new CropTypeRequestDTO(nomeCurto);
        Set<ConstraintViolation<CropTypeRequestDTO>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty());

        String mensagem = violations.iterator().next().getMessage();
        assertTrue(mensagem.contains("must be between 2 and 100 characters"));
    }

    @Test
    @DisplayName("Não deve criar DTO quando nome muito longo")
    void naoDeveCriarDTO_QuandoNomeMuitoLongo() {
        String nomeLongo = "A".repeat(101);

        CropTypeRequestDTO dto = new CropTypeRequestDTO(nomeLongo);
        Set<ConstraintViolation<CropTypeRequestDTO>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty());

        String mensagem = violations.iterator().next().getMessage();
        assertTrue(mensagem.contains("must be between 2 and 100 characters"));
    }

    @Test
    @DisplayName("Não deve criar DTO quando nome com caracteres inválidos")
    void naoDeveCriarDTO_QuandoNomeComCaracteresInvalidos() {
        String nomeInvalido = "Milho@#$%";

        CropTypeRequestDTO dto = new CropTypeRequestDTO(nomeInvalido);
        Set<ConstraintViolation<CropTypeRequestDTO>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Deve criar DTO quando nome com caracteres válidos especiais")
    void deveCriarDTO_QuandoNomeComCaracteresValidosEspeciais() {
        String nomeComEspacos = "Milho Verde";
        String nomeComHifen = "Feijão-Preto";
        String nomeComPonto = "Arroz Tipo 1.5";
        String nomeComVirgula = "Soja, Tipo A";

        assertTrue(validator.validate(new CropTypeRequestDTO(nomeComEspacos)).isEmpty());
        assertTrue(validator.validate(new CropTypeRequestDTO(nomeComHifen)).isEmpty());
        assertTrue(validator.validate(new CropTypeRequestDTO(nomeComPonto)).isEmpty());
        assertTrue(validator.validate(new CropTypeRequestDTO(nomeComVirgula)).isEmpty());
    }

    @Test
    @DisplayName("Deve criar DTO quando nome no limite mínimo")
    void deveCriarDTO_QuandoNomeLimiteMinimo() {
        String nomeMinimo = "AB";

        CropTypeRequestDTO dto = new CropTypeRequestDTO(nomeMinimo);
        Set<ConstraintViolation<CropTypeRequestDTO>> violations = validator.validate(dto);

        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("Deve criar DTO quando nome no limite máximo")
    void deveCriarDTO_QuandoNomeLimiteMaximo() {
        String nomeMaximo = "A".repeat(100);

        CropTypeRequestDTO dto = new CropTypeRequestDTO(nomeMaximo);
        Set<ConstraintViolation<CropTypeRequestDTO>> violations = validator.validate(dto);

        assertTrue(violations.isEmpty());
    }
}