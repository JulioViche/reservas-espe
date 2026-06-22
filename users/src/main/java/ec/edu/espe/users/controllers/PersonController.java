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

import ec.edu.espe.users.dtos.PersonCreateResponseDto;
import ec.edu.espe.users.dtos.PersonRequestDto;
import ec.edu.espe.users.dtos.PersonResponseDto;
import ec.edu.espe.users.services.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/persons")
@RequiredArgsConstructor
@Tag(name = "Personas", description = "Operaciones CRUD para personas del sistema")
public class PersonController {

    private final PersonService personService;

    @GetMapping
    @Operation(summary = "Listar todas las personas", description = "Obtiene todas las personas registradas en el sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de personas obtenida exitosamente", content = @Content(schema = @Schema(implementation = PersonResponseDto.class)))
    })
    public ResponseEntity<List<PersonResponseDto>> getAll() {
        return ResponseEntity.ok(personService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener persona por ID", description = "Busca una persona por su identificador único")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Persona encontrada", content = @Content(schema = @Schema(implementation = PersonResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Persona no encontrada")
    })
    public ResponseEntity<PersonResponseDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(personService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Crear una nueva persona", description = "Registra una nueva persona y genera automáticamente un usuario y contraseña")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Persona creada exitosamente", content = @Content(schema = @Schema(implementation = PersonCreateResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public ResponseEntity<PersonCreateResponseDto> create(@Valid @RequestBody PersonRequestDto request) {
        return new ResponseEntity<>(personService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una persona", description = "Actualiza la información de una persona existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Persona actualizada exitosamente", content = @Content(schema = @Schema(implementation = PersonResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Persona no encontrada"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public ResponseEntity<PersonResponseDto> update(@PathVariable UUID id,
            @Valid @RequestBody PersonRequestDto request) {
        return ResponseEntity.ok(personService.update(id, request));
    }

    @PatchMapping("/{id}/active")
    @Operation(summary = "Cambiar estado activo de una persona", description = "Activa o desactiva una persona en el sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Estado cambiado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Persona no encontrada")
    })
    public ResponseEntity<Void> toggleActive(@PathVariable UUID id) {
        personService.toggleActive(id);
        return ResponseEntity.noContent().build();
    }
}
