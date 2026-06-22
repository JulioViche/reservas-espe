package ec.edu.espe.parking.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.parking.dtos.SpaceRequestDto;
import ec.edu.espe.parking.dtos.SpaceResponseDto;
import ec.edu.espe.parking.services.SpaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/spaces")
@RequiredArgsConstructor
@Tag(name = "Espacios", description = "Operaciones CRUD para espacios de estacionamiento")
public class SpaceController {

    private final SpaceService spaceService;

    @GetMapping
    @Operation(summary = "Listar todos los espacios", description = "Obtiene todos los espacios de estacionamiento registrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de espacios obtenida exitosamente", content = @Content(schema = @Schema(implementation = SpaceResponseDto.class)))
    })
    public ResponseEntity<List<SpaceResponseDto>> getAll() {
        return ResponseEntity.ok(spaceService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener espacio por ID", description = "Busca un espacio de estacionamiento por su identificador único")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Espacio encontrado", content = @Content(schema = @Schema(implementation = SpaceResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Espacio no encontrado")
    })
    public ResponseEntity<SpaceResponseDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(spaceService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo espacio", description = "Registra un nuevo espacio de estacionamiento")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Espacio creado exitosamente", content = @Content(schema = @Schema(implementation = SpaceResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public ResponseEntity<SpaceResponseDto> create(@Valid @RequestBody SpaceRequestDto request) {
        return new ResponseEntity<>(spaceService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un espacio", description = "Actualiza la información de un espacio existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Espacio actualizado exitosamente", content = @Content(schema = @Schema(implementation = SpaceResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Espacio no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public ResponseEntity<SpaceResponseDto> update(@PathVariable UUID id,
            @Valid @RequestBody SpaceRequestDto request) {
        return ResponseEntity.ok(spaceService.update(id, request));
    }

    @PatchMapping("/{id}/enabled")
    @Operation(summary = "Cambiar estado de un espacio", description = "Activa o desactiva un espacio de estacionamiento")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Estado cambiado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Espacio no encontrado")
    })
    public ResponseEntity<Void> toggleEnabled(@PathVariable UUID id) {
        spaceService.toggleEnabled(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un espacio", description = "Elimina lógicamente un espacio de estacionamiento")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Espacio eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Espacio no encontrado")
    })
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        spaceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
