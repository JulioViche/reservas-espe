package ec.edu.espe.parking.dtos;

import java.util.UUID;

import ec.edu.espe.parking.entities.SpaceStatus;
import ec.edu.espe.parking.entities.SpaceType;
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
public class SpaceRequestDto {

    @NotNull(message = "El código del espacio es obligatorio")
    private UUID zoneId;

    @Size(max = 256, message = "La descripción del espacio debe tener menos de 256 caracteres")
    private String description;

    @NotNull(message = "El tipo de espacio es obligatorio")
    @Enumerated(EnumType.STRING)
    private SpaceType type;

    @NotNull(message = "El estado del espacio es obligatorio")
    @Enumerated(EnumType.STRING)
    private SpaceStatus status;
}
