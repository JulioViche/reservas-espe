package ec.edu.espe.users.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.users.dtos.RoleRequestDto;
import ec.edu.espe.users.dtos.RoleResponseDto;
import ec.edu.espe.users.services.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
@Tag(name = "Roles", description = "Operaciones CRUD para roles del sistema")
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    @Operation(summary = "Listar todos los roles", description = "Obtiene todos los roles registrados en el sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de roles obtenida exitosamente", content = @Content(schema = @Schema(implementation = RoleResponseDto.class)))
    })
    public ResponseEntity<List<RoleResponseDto>> getAll() {
        return ResponseEntity.ok(roleService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener rol por ID", description = "Busca un rol por su identificador único")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rol encontrado", content = @Content(schema = @Schema(implementation = RoleResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    public ResponseEntity<RoleResponseDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(roleService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo rol", description = "Registra un nuevo rol en el sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Rol creado exitosamente", content = @Content(schema = @Schema(implementation = RoleResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public ResponseEntity<RoleResponseDto> create(@Valid @RequestBody RoleRequestDto request) {
        return new ResponseEntity<>(roleService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un rol", description = "Actualiza la información de un rol existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rol actualizado exitosamente", content = @Content(schema = @Schema(implementation = RoleResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public ResponseEntity<RoleResponseDto> update(@PathVariable UUID id,
            @Valid @RequestBody RoleRequestDto request) {
        return ResponseEntity.ok(roleService.update(id, request));
    }

    @PatchMapping("/{id}/active")
    @Operation(summary = "Cambiar estado activo de un rol", description = "Activa o desactiva un rol en el sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Estado cambiado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    public ResponseEntity<Void> toggleActive(@PathVariable UUID id) {
        roleService.toggleActive(id);
        return ResponseEntity.noContent().build();
    }
}
