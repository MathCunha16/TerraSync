package com.terrasync.terrasync_backend.repository;

import com.terrasync.terrasync_backend.entity.CropType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CropTypeRepository extends JpaRepository<CropType, Long> {

    // Method de busca para nomes (seja minusculo ou ñ)
    Optional<CropType> findByNameIgnoreCase(String name);

    // Method pra verificar se já existe um nome para esse Crop Type
    boolean existsByNameIgnoreCase(String name);
}
