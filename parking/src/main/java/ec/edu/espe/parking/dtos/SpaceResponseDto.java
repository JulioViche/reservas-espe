package ec.edu.espe.parking.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import ec.edu.espe.parking.entities.SpaceStatus;
import ec.edu.espe.parking.entities.SpaceType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Respuesta con datos de un espacio de estacionamiento")
public class SpaceResponseDto {

    @Schema(description = "Identificador único del espacio")
    private UUID id;

    @Schema(description = "Código único del espacio", example = "S-001")
    private String code;

    @Schema(description = "Descripción del espacio", example = "Espacio cercano a la entrada")
    private String description;

    @Schema(description = "Tipo de espacio", example = "CAR")
    private SpaceType type;

    @Schema(description = "Estado del espacio", example = "AVAILABLE")
    private SpaceStatus status;

    @Schema(description = "Indica si el espacio está activo", example = "true")
    private boolean enabled;

    @Schema(description = "Fecha de creación")
    private LocalDateTime createdAt;

    @Schema(description = "Fecha de última actualización")
    private LocalDateTime updatedAt;

    @Schema(description = "Identificador de la zona a la que pertenece")
    private UUID zoneId;

    @Schema(description = "Nombre de la zona a la que pertenece", example = "Zona A")
    private String zoneName;
}
