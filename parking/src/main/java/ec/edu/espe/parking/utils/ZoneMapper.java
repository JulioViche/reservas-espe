package ec.edu.espe.parking.utils;

import org.springframework.stereotype.Component;

import ec.edu.espe.parking.dtos.ZoneRequestDto;
import ec.edu.espe.parking.dtos.ZoneResponseDto;
import ec.edu.espe.parking.entities.Zone;

@Component
public class ZoneMapper {

    private final SpaceMapper spaceMapper;

    public ZoneMapper(SpaceMapper spaceMapper) {
        this.spaceMapper = spaceMapper;
    }

    public Zone toEntity(ZoneRequestDto request) {
        return Zone.builder()
                .name(request.getName())
                .description(request.getDescription())
                .type(request.getType())
                .capacity(request.getCapacity())
                .build();
    }

    public ZoneResponseDto toResponseDto(Zone zone) {
        return ZoneResponseDto.builder()
                .id(zone.getId())
                .code(zone.getCode())
                .name(zone.getName())
                .description(zone.getDescription())
                .type(zone.getType())
                .capacity(zone.getCapacity())
                .enabled(zone.isEnabled())
                .createdAt(zone.getCreatedAt())
                .updatedAt(zone.getUpdatedAt())
                .spaces(zone.getSpaces().stream()
                        .map(spaceMapper::toResponseDto)
                        .toList())
                .build();
    }
}
