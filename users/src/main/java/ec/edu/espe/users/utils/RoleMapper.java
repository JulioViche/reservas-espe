package ec.edu.espe.users.utils;

import org.springframework.stereotype.Component;

import ec.edu.espe.users.dtos.RoleRequestDto;
import ec.edu.espe.users.dtos.RoleResponseDto;
import ec.edu.espe.users.entities.Role;

@Component
public class RoleMapper {

    public Role toEntity(RoleRequestDto request) {
        return Role.builder()
                .name(request.getName())
                .description(request.getDescription())
                .active(request.getActive())
                .build();
    }

    public RoleResponseDto toResponseDto(Role role) {
        return RoleResponseDto.builder()
                .id(role.getId())
                .name(role.getName())
                .description(role.getDescription())
                .active(role.getActive())
                .createdAt(role.getCreatedAt())
                .updatedAt(role.getUpdatedAt())
                .build();
    }
}
