package ec.edu.espe.parking.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import ec.edu.espe.parking.dtos.SpaceRequestDto;
import ec.edu.espe.parking.dtos.SpaceResponseDto;
import ec.edu.espe.parking.entities.Space;
import ec.edu.espe.parking.entities.SpaceStatus;
import ec.edu.espe.parking.repositories.SpaceRepository;
import ec.edu.espe.parking.services.SpaceService;
import ec.edu.espe.parking.utils.UtilsMappers;

public class SpaceServiceImpl implements SpaceService {

    private final SpaceRepository repository;
    private final UtilsMappers mapper;

    public SpaceServiceImpl(SpaceRepository spaceRepository, UtilsMappers utilsMappers) {
        this.repository = spaceRepository;
        this.mapper = utilsMappers;
    }

    @Override
    public List<SpaceResponseDto> getAllSpaces() {
        return repository.findAll().stream()
                .map(mapper::mapToSpaceResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public SpaceResponseDto createSpace(SpaceRequestDto request) {

        throw new UnsupportedOperationException("Unimplemented method 'createSpace'");
    }

    @Override
    public SpaceResponseDto updateSpace(UUID id, SpaceRequestDto request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateSpace'");
    }

    @Override
    public boolean deleteSpace(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteSpace'");
    }

    @Override
    public SpaceResponseDto changeSpaceStatus(UUID spaceId, SpaceStatus status) {
        Space space = repository.findById(spaceId)
                .orElseThrow(() -> new RuntimeException("Space not found with id: " + spaceId));

        if (space.getStatus() == status) {
            throw new RuntimeException("Space is already in the desired status: " + status);
        }

        space.setStatus(status);

        return mapper.mapToSpaceResponseDto(repository.save(space));
    }

    @Override
    public List<SpaceResponseDto> getSpacesByZoneAndStatus(UUID zoneId, SpaceStatus status) {
        List<Space> spaces = repository.findByZoneId(zoneId);
        return spaces.stream()
                .filter(s -> s.getStatus() == status)
                .map(mapper::mapToSpaceResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SpaceResponseDto> getSpacesByStatus(SpaceStatus status) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSpacesByStatus'");
    }
}
