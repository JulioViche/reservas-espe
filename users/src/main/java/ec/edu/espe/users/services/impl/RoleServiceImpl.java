package ec.edu.espe.users.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.espe.users.dtos.RoleRequestDto;
import ec.edu.espe.users.dtos.RoleResponseDto;
import ec.edu.espe.users.entities.Role;
import ec.edu.espe.users.repositories.RoleRepository;
import ec.edu.espe.users.services.RoleService;
import ec.edu.espe.users.utils.RoleMapper;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleResponseDto> getAll() {
        return roleRepository.findAll().stream()
                .map(roleMapper::toResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RoleResponseDto getById(UUID id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con id: " + id));
        return roleMapper.toResponseDto(role);
    }

    @Override
    @Transactional
    public RoleResponseDto create(RoleRequestDto request) {
        if (roleRepository.existsByName(request.getName())) {
            throw new IllegalArgumentException("El nombre del rol ya existe");
        }

        Role role = roleMapper.toEntity(request);
        return roleMapper.toResponseDto(roleRepository.save(role));
    }

    @Override
    @Transactional
    public RoleResponseDto update(UUID id, RoleRequestDto request) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado con id: " + id));

        if (!role.getName().equals(request.getName()) && roleRepository.existsByName(request.getName())) {
            throw new IllegalArgumentException("El nombre del rol ya existe");
        }

        role.setName(request.getName());
        role.setDescription(request.getDescription());
        role.setActive(request.getActive());

        return roleMapper.toResponseDto(roleRepository.save(role));
    }

    @Override
    @Transactional
    public void toggleActive(UUID id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado con id: " + id));
        role.setActive(!role.getActive());
    }
}
