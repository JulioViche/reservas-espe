package ec.edu.espe.parking.dtos;

import ec.edu.espe.parking.entities.ZoneType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class ZoneRequestDto {

    @NotBlank(message = "El nombre de la zona es obligatorio")
    @Size(max = 32, message = "El nombre de la zona debe tener menos de 32 caracteres")
    private String name;

    @Size(max = 256, message = "La descripción de la zona debe tener menos de 256 caracteres")
    private String description;

    @NotNull(message = "El tipo de zona es obligatorio")
    @Enumerated(EnumType.STRING)
    private ZoneType type;
}
