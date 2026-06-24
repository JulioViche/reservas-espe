package ec.edu.espe.parking.controllers;

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

import ec.edu.espe.parking.dtos.ZoneRequestDto;
import ec.edu.espe.parking.dtos.ZoneResponseDto;
import ec.edu.espe.parking.services.ZoneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/zones")
@RequiredArgsConstructor
@Tag(name = "Zonas", description = "Operaciones CRUD para zonas de estacionamiento")
public class ZoneController {

    private final ZoneService zoneService;

    @GetMapping
    @Operation(summary = "Listar todas las zonas", description = "Obtiene todas las zonas de estacionamiento registradas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de zonas obtenida exitosamente", content = @Content(schema = @Schema(implementation = ZoneResponseDto.class)))
    })
    public ResponseEntity<List<ZoneResponseDto>> getAll() {
        return ResponseEntity.ok(zoneService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener zona por ID", description = "Busca una zona de estacionamiento por su identificador único")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Zona encontrada", content = @Content(schema = @Schema(implementation = ZoneResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Zona no encontrada")
    })
    public ResponseEntity<ZoneResponseDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(zoneService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Crear una nueva zona", description = "Registra una nueva zona de estacionamiento con la información proporcionada")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Zona creada exitosamente", content = @Content(schema = @Schema(implementation = ZoneResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public ResponseEntity<ZoneResponseDto> create(@Valid @RequestBody ZoneRequestDto request) {
        return new ResponseEntity<>(zoneService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una zona", description = "Actualiza la información de una zona existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Zona actualizada exitosamente", content = @Content(schema = @Schema(implementation = ZoneResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Zona no encontrada"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public ResponseEntity<ZoneResponseDto> update(@PathVariable UUID id,
            @Valid @RequestBody ZoneRequestDto request) {
        return ResponseEntity.ok(zoneService.update(id, request));
    }

    @PatchMapping("/{id}/enabled")
    @Operation(summary = "Cambiar estado de una zona", description = "Activa o desactiva una zona de estacionamiento. La desactivación se propaga en cascada a todos los espacios de la zona.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Estado cambiado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Zona no encontrada")
    })
    public ResponseEntity<Void> toggleEnabled(@PathVariable UUID id) {
        zoneService.toggleEnabled(id);
        return ResponseEntity.noContent().build();
    }
}
