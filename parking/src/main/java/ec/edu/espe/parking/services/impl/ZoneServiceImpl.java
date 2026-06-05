package ec.edu.espe.parking.services.impl;

import ec.edu.espe.parking.repositories.SpaceRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.espe.parking.dtos.ZoneRequestDto;
import ec.edu.espe.parking.dtos.ZoneResponseDto;
import ec.edu.espe.parking.entities.Space;
import ec.edu.espe.parking.entities.SpaceStatus;
import ec.edu.espe.parking.entities.Zone;
import ec.edu.espe.parking.entities.ZoneType;
import ec.edu.espe.parking.repositories.ZoneRepository;
import ec.edu.espe.parking.services.ZoneService;
import ec.edu.espe.parking.utils.ZoneMapper;

@Service
public class ZoneServiceImpl implements ZoneService {

    private final SpaceRepository spaceRepository;

    private final ZoneRepository zoneRepository;

    private final ZoneMapper zoneMapper;

    public ZoneServiceImpl(ZoneRepository zoneRepository, SpaceRepository spaceRepository, ZoneMapper zoneMapper) {
        this.zoneRepository = zoneRepository;
        this.spaceRepository = spaceRepository;
        this.zoneMapper = zoneMapper;
    }

    private String generateUniqueCode(ZoneType type) {
        String prefix = "ZONE-" + type.name().substring(0, 3) + "-";
        int max = zoneRepository.findByCodeStartingWith(prefix).stream()
                .mapToInt(z -> Integer.parseInt(z.getCode().replace(prefix, "")))
                .max()
                .orElse(0);
        return prefix + String.format("%02d", max + 1);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ZoneResponseDto> getAll() {
        return zoneRepository.findAll().stream()
                .map(zoneMapper::toResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ZoneResponseDto getById(UUID id) {
        Zone zone = zoneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Zona no encontrada con id: " + id));
        return zoneMapper.toResponseDto(zone);
    }

    @Override
    @Transactional
    public ZoneResponseDto create(ZoneRequestDto request) {
        if (zoneRepository.existsByName(request.getName()))
            throw new IllegalArgumentException("El nombre de la zona ya existe");

        Zone zone = zoneMapper.toEntity(request);
        zone.setCode(generateUniqueCode(request.getType()));
        zone.setEnabled(true);
        zone.setCreatedAt(LocalDateTime.now());
        zone.setUpdatedAt(LocalDateTime.now());

        return zoneMapper.toResponseDto(zoneRepository.save(zone));
    }

    @Override
    @Transactional
    public ZoneResponseDto update(UUID id, ZoneRequestDto request) {
        Zone zone = zoneRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Zona no encontrada con id: " + id));

        if (!zone.getName().equals(request.getName()) && zoneRepository.existsByName(request.getName()))
            throw new IllegalArgumentException("El nombre de la zona ya existe");

        if (zone.getSpaces().size() > 0 && zone.getType() != request.getType())
            throw new IllegalStateException("No se puede cambiar el tipo de la zona porque tiene espacios asociados");

        if (zone.getSpaces().size() > request.getCapacity())
            throw new IllegalStateException(
                    "La nueva capacidad no puede ser menor que la cantidad de espacios asociados");

        if (zone.getType() != request.getType())
            zone.setCode(generateUniqueCode(request.getType()));
        zone.setName(request.getName());
        zone.setDescription(request.getDescription());
        zone.setType(request.getType());
        zone.setCapacity(request.getCapacity());
        zone.setUpdatedAt(LocalDateTime.now());

        return zoneMapper.toResponseDto(zoneRepository.save(zone));
    }

    @Override
    @Transactional
    public void toggleEnabled(UUID id) {
        Zone zone = zoneRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Zona no encontrada con id: " + id));

        if (spaceRepository.countByZoneIdAndStatus(zone.getId(), SpaceStatus.OCCUPIED) > 0)
            throw new IllegalStateException("No se puede desactivar la zona porque tiene espacios ocupados");

        zone.setEnabled(!zone.isEnabled());
        zone.setUpdatedAt(LocalDateTime.now());

        List<Space> spaces = spaceRepository.findByZoneId(zone.getId());
        for (Space space : spaces) {
            space.setEnabled(zone.isEnabled());
            space.setUpdatedAt(LocalDateTime.now());
        }
    }
}
