package ec.edu.espe.parking.utils;

import ec.edu.espe.parking.dtos.SpaceRequestDto;
import ec.edu.espe.parking.dtos.SpaceResponseDto;
import ec.edu.espe.parking.entities.Space;

public class UtilsMappers {
    public Space mapToSpaceEntity(SpaceRequestDto spaceRequestDto) {
        return Space.builder()
                .id(spaceRequestDto.getZoneId())
                .description(spaceRequestDto.getDescription())
                .type(spaceRequestDto.getType())
                .status(spaceRequestDto.getStatus())
                .build();
    }

    public SpaceResponseDto mapToSpaceResponseDto(Space space) {
        return SpaceResponseDto.builder()
                .id(space.getId())
                .code(space.getCode())
                .description(space.getDescription())
                .type(space.getType())
                .status(space.getStatus())
                .isActive(space.isActive())
                .zone(space.getZone())
                .createdAt(space.getCreatedAt())
                .updatedAt(space.getUpdatedAt())
                .build();
    }
}
