package com.terrasync.backend.mapper;

import com.terrasync.backend.dto.farm.GeolocationDTO;
import com.terrasync.backend.dto.farm.FarmRequestDTO;
import com.terrasync.backend.dto.farm.FarmResponseDTO;
import com.terrasync.backend.entity.Farm;
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

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "active", target = "isActive")
    FarmResponseDTO toResponseDTO(Farm farm);

    List<FarmResponseDTO> toResponseDTOList(List<Farm> farms);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Farm toEntity(FarmRequestDTO farmRequestDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateFromDTO(FarmRequestDTO dto, @MappingTarget Farm entity);


    // ===================================================================
    // Métodos de Ajuda para Geolocation (Point ↔ GeolocationDTO)
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
