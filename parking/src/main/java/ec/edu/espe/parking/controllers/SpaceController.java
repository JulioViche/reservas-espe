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
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/spaces")
@RequiredArgsConstructor
public class SpaceController {

    private final SpaceService spaceService;

    @GetMapping
    public ResponseEntity<List<SpaceResponseDto>> getAll() {
        // 200
        return ResponseEntity.ok(spaceService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpaceResponseDto> getById(@PathVariable UUID id) {
        // 200
        return ResponseEntity.ok(spaceService.getById(id));
    }

    @PostMapping
    public ResponseEntity<SpaceResponseDto> create(@Valid @RequestBody SpaceRequestDto request) {
        // 201
        return new ResponseEntity<>(spaceService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpaceResponseDto> update(@PathVariable UUID id,
            @Valid @RequestBody SpaceRequestDto request) {
        // 200
        return ResponseEntity.ok(spaceService.update(id, request));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> toggleEnabled(@PathVariable UUID id) {
        spaceService.toggleEnabled(id);
        // 204
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        spaceService.delete(id);
        // 204
        return ResponseEntity.noContent().build();
    }
}
