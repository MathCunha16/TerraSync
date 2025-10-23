package com.terrasync.terrasyncBackend.repository;

import com.terrasync.terrasyncBackend.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FarmRepository extends JpaRepository<Farm, Long> {

    // Busca no banco de dados todas as Farm onde o user_id corresponde ao ID fornecido e onde a coluna is_active é true
    List<Farm> findAllByUser_IdAndIsActiveTrue(Long userId);

    // Busca uma Farm que tenha um 'id' específico E que pertença a um user_id específico
    Optional<Farm> findByIdAndUser_Id(Long farmId, Long userId);

    // Busca uma farm pelo nome e 'ID' do utilizador
    Optional<Farm> findByNameIgnoreCaseAndUser_Id(String name, Long userId);

    // Valida nomes duplicados (por utilizador)
    boolean existsByNameIgnoreCaseAndUser_Id(String name, Long userId);
}
