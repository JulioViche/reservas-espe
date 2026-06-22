package ec.edu.espe.parking.dtos;

import ec.edu.espe.parking.entities.ZoneType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
@Schema(description = "Solicitud para crear o actualizar una zona de estacionamiento")
public class ZoneRequestDto {

    @NotBlank(message = "El nombre de la zona es obligatorio")
    @Size(max = 32, message = "El nombre de la zona debe tener menos de 32 caracteres")
    @Schema(description = "Nombre de la zona", example = "Zona A", maxLength = 32)
    private String name;

    @Size(max = 256, message = "La descripci\u00f3n de la zona debe tener menos de 256 caracteres")
    @Schema(description = "Descripción de la zona", example = "Zona principal de estacionamiento", maxLength = 256)
    private String description;

    @NotNull(message = "El tipo de zona es obligatorio")
    @Enumerated(EnumType.STRING)
    @Schema(description = "Tipo de zona", example = "REGULAR", allowableValues = { "VIP", "REGULAR", "INTERNAL", "EXTERNAL", "PREFERENTIAL" })
    private ZoneType type;

    @Min(1)
    @Max(100)
    @Schema(description = "Capacidad máxima de la zona", example = "50", minimum = "1", maximum = "100")
    private int capacity;
}
