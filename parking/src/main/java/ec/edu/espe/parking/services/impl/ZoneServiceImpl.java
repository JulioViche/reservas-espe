package ec.edu.espe.parking.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.espe.parking.dtos.ZoneRequestDto;
import ec.edu.espe.parking.dtos.ZoneResponseDto;
import ec.edu.espe.parking.entities.Zone;
import ec.edu.espe.parking.entities.ZoneType;
import ec.edu.espe.parking.repositories.ZoneRepository;
import ec.edu.espe.parking.services.ZoneService;
import jakarta.transaction.Transactional;

@Service
public class ZoneServiceImpl implements ZoneService {

    @Autowired
    private ZoneRepository zoneRepository;

    private ZoneResponseDto mapToResponseDto(Zone zone) {

        return ZoneResponseDto.builder()
                .id(zone.getId())
                .code(zone.getCode())
                .name(zone.getName())
                .description(zone.getDescription())
                .isActive(zone.isActive())
                .type(zone.getType())
                .spaces(zone.getSpaces())
                .createdAt(zone.getCreatedAt())
                .updatedAt(zone.getUpdatedAt())
                .build();
    }

    private String generateUniqueCode(ZoneType type) {
        String prefix = "ZONE-" + type.name().substring(0, 3) + "-";
        long count = zoneRepository.countByCodeStartingWith(prefix);
        return prefix + String.format("%02d", count + 1);
    }

    @Transactional
    public List<ZoneResponseDto> getAllZones() {

        return zoneRepository.findAll().stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ZoneResponseDto createZone(ZoneRequestDto request) {
        if (zoneRepository.existsByName(request.getName())) {
            throw new IllegalArgumentException("El nombre de la zona ya existe");
        }

        Zone zone = Zone.builder()
                .name(request.getName())
                .description(request.getDescription())
                .isActive(true)
                .type(request.getType())
                .build();

        return null;
    }

    @Transactional
    public ZoneResponseDto updateZone(UUID id, ZoneRequestDto request) {
        return null;
    }

    @Transactional
    public void switchZoneStatus(UUID id) {
    }
}
