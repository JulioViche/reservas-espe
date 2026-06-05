package ec.edu.espe.parking.utils;

import org.springframework.stereotype.Component;

import ec.edu.espe.parking.dtos.SpaceRequestDto;
import ec.edu.espe.parking.dtos.SpaceResponseDto;
import ec.edu.espe.parking.entities.Space;
import ec.edu.espe.parking.entities.Zone;

@Component
public class SpaceMapper {

    public Space toEntity(SpaceRequestDto request, Zone zone) {
        return Space.builder()
                .description(request.getDescription())
                .type(request.getType())
                .status(request.getStatus())
                .zone(zone)
                .build();
    }

    public SpaceResponseDto toResponseDto(Space space) {
        return SpaceResponseDto.builder()
                .id(space.getId())
                .code(space.getCode())
                .description(space.getDescription())
                .type(space.getType())
                .status(space.getStatus())
                .enabled(space.isEnabled())
                .createdAt(space.getCreatedAt())
                .updatedAt(space.getUpdatedAt())
                .zoneId(space.getZone().getId())
                .zoneName(space.getZone().getName())
                .build();
    }
}
