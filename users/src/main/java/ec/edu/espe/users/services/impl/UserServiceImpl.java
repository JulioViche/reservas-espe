package ec.edu.espe.users.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.espe.users.dtos.UserResponseDto;
import ec.edu.espe.users.dtos.UserRoleRequestDto;
import ec.edu.espe.users.entities.Role;
import ec.edu.espe.users.entities.User;
import ec.edu.espe.users.entities.UserRole;
import ec.edu.espe.users.entities.UserRoleId;
import ec.edu.espe.users.repositories.RoleRepository;
import ec.edu.espe.users.repositories.UserRepository;
import ec.edu.espe.users.repositories.UserRoleRepository;
import ec.edu.espe.users.services.UserService;
import ec.edu.espe.users.utils.UserMapper;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository,
            RoleRepository roleRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getById(UUID idPerson) {
        User user = userRepository.findById(idPerson)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + idPerson));
        return userMapper.toResponseDto(user);
    }

    @Override
    @Transactional
    public void assignRole(UserRoleRequestDto request) {
        if (userRoleRepository.existsByIdIdUserAndIdIdRole(request.getIdUser(), request.getIdRole())) {
            throw new IllegalArgumentException("El rol ya está asignado al usuario");
        }

        User user = userRepository.findById(request.getIdUser())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        Role role = roleRepository.findById(request.getIdRole())
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado"));

        UserRoleId id = new UserRoleId(request.getIdUser(), request.getIdRole());
        UserRole userRole = UserRole.builder()
                .id(id)
                .user(user)
                .role(role)
                .build();
        userRoleRepository.save(userRole);
    }

    @Override
    @Transactional
    public void removeRole(UserRoleRequestDto request) {
        UserRoleId id = new UserRoleId(request.getIdUser(), request.getIdRole());
        if (!userRoleRepository.existsById(id)) {
            throw new IllegalArgumentException("El rol no está asignado al usuario");
        }
        userRoleRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void toggleActive(UUID idPerson) {
        User user = userRepository.findById(idPerson)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con id: " + idPerson));

        boolean newActive = !user.getActive();
        if (newActive && !user.getPerson().getActive()) {
            throw new IllegalStateException("No se puede activar el usuario porque la persona está inactiva");
        }

        user.setActive(newActive);
    }
}
