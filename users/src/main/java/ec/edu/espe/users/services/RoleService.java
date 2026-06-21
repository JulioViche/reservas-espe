package ec.edu.espe.users.services;

import java.util.List;
import java.util.UUID;

import ec.edu.espe.users.dtos.RoleRequestDto;
import ec.edu.espe.users.dtos.RoleResponseDto;

public interface RoleService {

    List<RoleResponseDto> getAll();

    RoleResponseDto getById(UUID id);

    RoleResponseDto create(RoleRequestDto request);

    RoleResponseDto update(UUID id, RoleRequestDto request);

    void toggleActive(UUID id);
}
