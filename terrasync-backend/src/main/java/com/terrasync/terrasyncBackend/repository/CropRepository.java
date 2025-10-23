package com.terrasync.terrasyncBackend.repository;

import com.terrasync.terrasyncBackend.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {

    // Verifica se existe algum Crop associado a um determinado tipo de CropType.
    boolean existsByCropTypeId(Long cropTypeId);

    // Encontra todas as culturas de uma fazenda específica.
    List<Crop> findAllByFarm_Id(Long farmId);

    // Encontra uma cultura específica pelo seu 'ID' e pelo 'ID' da sua fazenda.
    Optional<Crop> findByIdAndFarm_Id(Long cropId, Long farmId);

    // Verifica se uma cultura com um determinado nome já existe numa fazenda específica.
    boolean existsByNameIgnoreCaseAndFarm_Id(String name, Long farmId);
}

