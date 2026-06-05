package ec.edu.espe.parking.services;

import java.util.List;
import java.util.UUID;

import ec.edu.espe.parking.dtos.SpaceRequestDto;
import ec.edu.espe.parking.dtos.SpaceResponseDto;

public interface SpaceService {

    List<SpaceResponseDto> getAll();

    SpaceResponseDto getById(UUID id);

    SpaceResponseDto create(SpaceRequestDto request);

    SpaceResponseDto update(UUID id, SpaceRequestDto request);

    void toggleEnabled(UUID id);

    void delete(UUID id);
}
