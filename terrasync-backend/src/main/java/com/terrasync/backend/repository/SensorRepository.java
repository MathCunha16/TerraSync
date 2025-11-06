package com.terrasync.backend.repository;

import com.terrasync.backend.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {

    // Encontra todos os sensores associados a uma cultura específica.
    List<Sensor> findAllByCrop_Id(Long cropId);

    // Encontra um sensor pelo seu ID e pelo ID do usuário proprietário da fazenda à qual a cultura está associada.
    Optional<Sensor> findByIdAndCrop_Farm_User_Id(Long sensorId, Long userId);

    // Encontra um sensor pelo seu device_uuid.
    Optional<Sensor> findByDeviceUuid(String deviceUuid);

    // Verifica se um sensor com um determinado device_uuid já existe.
    boolean existsByDeviceUuid(String deviceUuid);

    // Verifica se existe algum Sensor associado a uma determinada cultura.
    boolean existsByCropId(Long cropId);
}
