package ec.edu.espe.parking.dtos;

import java.util.UUID;

import ec.edu.espe.parking.entities.SpaceStatus;
import ec.edu.espe.parking.entities.SpaceType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Solicitud para crear o actualizar un espacio de estacionamiento")
public class SpaceRequestDto {

    @Size(max = 256, message = "La descripci\u00f3n del espacio debe tener menos de 256 caracteres")
    @Schema(description = "Descripción del espacio", example = "Espacio cercano a la entrada", maxLength = 256)
    private String description;

    @NotNull(message = "El tipo de espacio es obligatorio")
    @Enumerated(EnumType.STRING)
    @Schema(description = "Tipo de espacio", example = "CAR", allowableValues = { "BIKE", "CAR", "TRUCK" })
    private SpaceType type;

    @NotNull(message = "El estado del espacio es obligatorio")
    @Enumerated(EnumType.STRING)
    @Schema(description = "Estado del espacio", example = "AVAILABLE", allowableValues = { "AVAILABLE", "OCCUPIED", "RESERVED", "MAINTENANCE" })
    private SpaceStatus status;

    @NotNull(message = "El identificador de la zona es obligatorio")
    @Schema(description = "Identificador de la zona a la que pertenece", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID zoneId;
}
