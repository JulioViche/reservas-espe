package ec.edu.espe.users.services;

import java.util.List;
import java.util.UUID;

import ec.edu.espe.users.dtos.UserResponseDto;
import ec.edu.espe.users.dtos.UserRoleRequestDto;

public interface UserService {

    List<UserResponseDto> getAll();

    UserResponseDto getById(UUID idPerson);

    void assignRole(UserRoleRequestDto request);

    void removeRole(UserRoleRequestDto request);

    void toggleActive(UUID idPerson);
}
