package com.terrasync.terrasync_backend.mapper;

import com.terrasync.terrasync_backend.dto.farm.GeolocationDTO;
import com.terrasync.terrasync_backend.dto.farm.FarmRequestDTO;
import com.terrasync.terrasync_backend.dto.farm.FarmResponseDTO;
import com.terrasync.terrasync_backend.entity.Farm;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface FarmMapper {

    @Mapping(source = "user.id", target = "userId") // Mapeia o 'ID' de 'user'
    FarmResponseDTO toResponseDTO(Farm farm);

    List<FarmResponseDTO> toResponseDTOList(List<Farm> farms);

    // Ignora o 'user' porque ele será definido no serviço com o utilizador autenticado
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "id", ignore = true) // Ignora o 'ID' na criação
    Farm toEntity(FarmRequestDTO farmRequestDTO);

    // Ignora o 'user' e o 'id' para não serem atualizados a partir do DTO
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateFromDTO(FarmRequestDTO dto, @MappingTarget Farm entity);


    // ===================================================================
    // Métodos de Ajuda para Geolocation (Point <=> GeolocationDTO)
    // O MapStruct vai usar estes métodos automaticamente quando encontrar estes tipos.
    // ===================================================================

    // Dicionário de Point para GeolocationDTO
    default GeolocationDTO pointToGeolocationDTO(Point point) {
        if (point == null) {
            return null;
        }
        return new GeolocationDTO(
                BigDecimal.valueOf(point.getY()), // Latitude é o eixo Y
                BigDecimal.valueOf(point.getX())  // Longitude é o eixo X
        );
    }

    // Dicionário de GeolocationDTO para Point
    default Point geolocationDTOToPoint(GeolocationDTO dto) {
        if (dto == null || dto.latitude() == null || dto.longitude() == null) {
            return null;
        }
        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate coordinate = new Coordinate(dto.longitude().doubleValue(), dto.latitude().doubleValue());
        return geometryFactory.createPoint(coordinate);
    }
}
