package ec.edu.espe.parking.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.espe.parking.dtos.SpaceResponseDto;
import ec.edu.espe.parking.dtos.ZoneRequestDto;
import ec.edu.espe.parking.dtos.ZoneResponseDto;
import ec.edu.espe.parking.entities.Space;
import ec.edu.espe.parking.entities.Zone;
import ec.edu.espe.parking.entities.ZoneType;
import ec.edu.espe.parking.repositories.ZoneRepository;
import ec.edu.espe.parking.services.ZoneService;
import jakarta.transaction.Transactional;

@Service
public class ZoneServiceImpl implements ZoneService {

    @Autowired
    private ZoneRepository zoneRepository;

    // TODO: mover a SpaceService cuando se cree
    private SpaceResponseDto mapToSpaceResponseDto(Space space) {
        return SpaceResponseDto.builder()
                .id(space.getId())
                .code(space.getCode())
                .description(space.getDescription())
                .isActive(space.isActive())
                .type(space.getType())
                .createdAt(space.getCreatedAt())
                .updatedAt(space.getUpdatedAt())
                .build();
    }

    private ZoneResponseDto mapToResponseDto(Zone zone) {

        return ZoneResponseDto.builder()
                .id(zone.getId())
                .code(zone.getCode())
                .name(zone.getName())
                .description(zone.getDescription())
                .isActive(zone.isActive())
                .type(zone.getType())
                .spaces(zone.getSpaces().stream()
                        .map(this::mapToSpaceResponseDto)
                        .collect(Collectors.toList()))
                .createdAt(zone.getCreatedAt())
                .updatedAt(zone.getUpdatedAt())
                .build();
    }

    private String generateUniqueCode(ZoneType type) {
        String prefix = "ZONE-" + type.name().substring(0, 3) + "-";
        int max = zoneRepository.findByCodeStartingWith(prefix).stream()
                .mapToInt(z -> Integer.parseInt(z.getCode().replace(prefix, "")))
                .max()
                .orElse(0);
        return prefix + String.format("%02d", max + 1);
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

        Zone zone = new Zone();
        zone.setName(request.getName());
        zone.setDescription(request.getDescription());
        zone.setCode(generateUniqueCode(request.getType()));
        zone.setActive(true);
        zone.setType(request.getType());
        zone.setCreatedAt(LocalDateTime.now());
        zone.setUpdatedAt(LocalDateTime.now());

        return mapToResponseDto(zoneRepository.save(zone));
    }

    @Transactional
    public ZoneResponseDto updateZone(UUID id, ZoneRequestDto request) {
        Zone zone = zoneRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La zona con id " + id + " no existe"));

        if (!zone.getName().equals(request.getName()) && zoneRepository.existsByName(request.getName())) {
            throw new IllegalArgumentException("El nombre de la zona ya existe");
        }

        zone.setName(request.getName());
        zone.setDescription(request.getDescription());

        ZoneType previousType = zone.getType();
        zone.setType(request.getType());

        if (previousType != request.getType()) {
            zone.setCode(generateUniqueCode(request.getType()));
        }

        zone.setUpdatedAt(LocalDateTime.now());

        return mapToResponseDto(zoneRepository.save(zone));
    }

    @Transactional
    public void switchZoneStatus(UUID id) {
        Zone zone = zoneRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La zona con id " + id + " no existe"));
        zone.setActive(!zone.isActive());
        zone.setUpdatedAt(LocalDateTime.now());
    }
}
