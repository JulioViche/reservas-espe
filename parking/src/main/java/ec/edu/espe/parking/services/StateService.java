package ec.edu.espe.parking.services;

import java.util.List;
import java.util.UUID;

import ec.edu.espe.parking.dtos.SpaceRequestDto;
import ec.edu.espe.parking.dtos.SpaceResponseDto;
import ec.edu.espe.parking.entities.SpaceStatus;

public interface StateService {

    List<SpaceResponseDto> getAllSpaces();

    SpaceResponseDto createSpace(SpaceRequestDto request);

    SpaceResponseDto updateSpace(UUID id, SpaceRequestDto request);

    boolean deleteSpace(UUID id);

    SpaceResponseDto changeSpaceStatus(UUID spaceId, SpaceStatus status);

    List<SpaceResponseDto> getSpacesByZoneAndStatus(UUID zoneId, SpaceStatus status);

    List<SpaceResponseDto> getSpacesByStatus(SpaceStatus status);
}
