package ec.edu.espe.parking.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import ec.edu.espe.parking.entities.ZoneType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Respuesta con datos de una zona de estacionamiento")
public class ZoneResponseDto {

    @Schema(description = "Identificador único de la zona")
    private UUID id;

    @Schema(description = "Código único de la zona", example = "ZONE-REG-01")
    private String code;

    @Schema(description = "Nombre de la zona", example = "Zona A")
    private String name;

    @Schema(description = "Descripción de la zona", example = "Zona principal de estacionamiento")
    private String description;

    @Schema(description = "Tipo de zona", example = "REGULAR")
    private ZoneType type;

    @Schema(description = "Capacidad máxima", example = "50")
    private int capacity;

    @Schema(description = "Indica si la zona está activa", example = "true")
    private boolean enabled;

    @Schema(description = "Fecha de creación")
    private LocalDateTime createdAt;

    @Schema(description = "Fecha de última actualización")
    private LocalDateTime updatedAt;

    @Schema(description = "Lista de espacios pertenecientes a la zona")
    private List<SpaceResponseDto> spaces;
}
