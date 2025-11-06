package com.terrasync.backend.repository;

import com.terrasync.backend.entity.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, Long> {

    // Encontra todos os dados de um sensor específico, ordenados pela data de leitura descendente.
    List<SensorData> findAllBySensor_IdOrderByReadAtDesc(Long sensorId);

    // Encontra todos os dados de um sensor específico que pertencem a um usuário.
    List<SensorData> findAllBySensor_IdAndSensor_Crop_Farm_User_Id(Long sensorId, Long userId);
}
