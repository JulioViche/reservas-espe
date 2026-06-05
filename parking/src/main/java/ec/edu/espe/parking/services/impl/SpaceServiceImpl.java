package ec.edu.espe.parking.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.espe.parking.dtos.SpaceRequestDto;
import ec.edu.espe.parking.dtos.SpaceResponseDto;
import ec.edu.espe.parking.entities.Space;
import ec.edu.espe.parking.entities.SpaceStatus;
import ec.edu.espe.parking.entities.Zone;
import ec.edu.espe.parking.repositories.SpaceRepository;
import ec.edu.espe.parking.repositories.ZoneRepository;
import ec.edu.espe.parking.services.SpaceService;
import ec.edu.espe.parking.utils.SpaceMapper;

@Service
public class SpaceServiceImpl implements SpaceService {

    private final SpaceRepository spaceRepository;

    private final ZoneRepository zoneRepository;

    private final SpaceMapper spaceMapper;

    public SpaceServiceImpl(SpaceRepository spaceRepository, ZoneRepository zoneRepository, SpaceMapper mapper) {
        this.spaceRepository = spaceRepository;
        this.zoneRepository = zoneRepository;
        this.spaceMapper = mapper;
    }

    private String generateUniqueCode(Zone zone) {
        String prefix = zone.getCode() + "-";
        int max = spaceRepository.findByCodeStartingWith(prefix).stream()
                .mapToInt(s -> Integer.parseInt(s.getCode().replace(prefix, "")))
                .max()
                .orElse(0);
        return prefix + String.format("%03d", max + 1);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SpaceResponseDto> getAll() {
        return spaceRepository.findAll().stream()
                .map(spaceMapper::toResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SpaceResponseDto getById(UUID id) {
        Space space = spaceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Espacio no encontrado con id: " + id));
        return spaceMapper.toResponseDto(space);
    }

    @Override
    @Transactional
    public SpaceResponseDto create(SpaceRequestDto request) {
        Zone zone = zoneRepository.findById(request.getZoneId())
                .orElseThrow(() -> new RuntimeException("Zona no encontrada: " + request.getZoneId()));

        if (zone.getSpaces().size() + 1 > zone.getCapacity())
            throw new RuntimeException("La zona no tiene capacidad para más espacios");

        Space space = spaceMapper.toEntity(request, zone);
        space.setCode(generateUniqueCode(zone));
        space.setStatus(SpaceStatus.AVAILABLE);
        space.setEnabled(true);
        space.setCreatedAt(LocalDateTime.now());
        space.setUpdatedAt(LocalDateTime.now());

        return spaceMapper.toResponseDto(spaceRepository.save(space));
    }

    @Override
    @Transactional
    public SpaceResponseDto update(UUID id, SpaceRequestDto request) {
        Space space = spaceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Espacio no encontrado con id: " + id));

        if (!space.getZone().getId().equals(request.getZoneId()))
            throw new RuntimeException("No se puede cambiar la zona de un espacio");

        if (request.getStatus() == SpaceStatus.OCCUPIED && !space.isEnabled())
            throw new RuntimeException("No se puede ocupar un espacio deshabilitado");

        space.setDescription(request.getDescription());
        space.setType(request.getType());
        space.setStatus(request.getStatus());
        space.setUpdatedAt(LocalDateTime.now());

        return spaceMapper.toResponseDto(spaceRepository.save(space));

    }

    @Override
    @Transactional
    public void toggleEnabled(UUID id) {
        Space space = spaceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Espacio no encontrado con id: " + id));

        if (space.getStatus() == SpaceStatus.OCCUPIED && space.isEnabled())
            throw new RuntimeException("No se puede deshabilitar un espacio ocupado");

        space.setEnabled(!space.isEnabled());
        space.setUpdatedAt(LocalDateTime.now());
        spaceRepository.save(space);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Space space = spaceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Espacio no encontrado con id: " + id));

        if (space.getStatus() == SpaceStatus.OCCUPIED)
            throw new RuntimeException("No se puede eliminar un espacio ocupado");

        spaceRepository.delete(space);
    }
}
