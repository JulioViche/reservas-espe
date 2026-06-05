package ec.edu.espe.parking.services;

import java.util.List;
import java.util.UUID;

import ec.edu.espe.parking.dtos.ZoneRequestDto;
import ec.edu.espe.parking.dtos.ZoneResponseDto;

public interface ZoneService {

    List<ZoneResponseDto> getAll();

    ZoneResponseDto getById(UUID id);

    ZoneResponseDto create(ZoneRequestDto request);

    ZoneResponseDto update(UUID id, ZoneRequestDto request);

    void toggleEnabled(UUID id);
}
