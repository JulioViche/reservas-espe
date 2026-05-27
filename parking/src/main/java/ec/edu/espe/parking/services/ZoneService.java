package ec.edu.espe.parking.services;

import java.util.List;
import java.util.UUID;

import ec.edu.espe.parking.dtos.ZoneRequestDto;
import ec.edu.espe.parking.dtos.ZoneResponseDto;

public interface ZoneService {

    List<ZoneResponseDto> getAllZones();

    ZoneResponseDto createZone(ZoneRequestDto request);

    ZoneResponseDto updateZone(UUID id, ZoneRequestDto request);

    void switchZoneStatus(UUID id);
}
