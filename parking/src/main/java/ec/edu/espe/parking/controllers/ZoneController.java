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
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/zones")
@RequiredArgsConstructor
public class ZoneController {

    private final ZoneService zoneService;

    @GetMapping
    public ResponseEntity<List<ZoneResponseDto>> getAll() {
        // 200
        return ResponseEntity.ok(zoneService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZoneResponseDto> getById(@PathVariable UUID id) {
        // 200
        return ResponseEntity.ok(zoneService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ZoneResponseDto> create(@Valid @RequestBody ZoneRequestDto request) {
        // 201
        return new ResponseEntity<>(zoneService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZoneResponseDto> update(@PathVariable UUID id,
            @Valid @RequestBody ZoneRequestDto request) {
        // 200
        return ResponseEntity.ok(zoneService.update(id, request));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> toggleEnabled(@PathVariable UUID id) {
        zoneService.toggleEnabled(id);
        // 204
        return ResponseEntity.noContent().build();
    }
}
