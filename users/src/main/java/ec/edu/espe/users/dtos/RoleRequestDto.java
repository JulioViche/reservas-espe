package ec.edu.espe.users.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Solicitud para crear o actualizar un rol")
public class RoleRequestDto {

    @NotBlank(message = "El nombre del rol es obligatorio")
    @Size(max = 50, message = "El nombre del rol debe tener menos de 50 caracteres")
    @Schema(description = "Nombre del rol", example = "ADMIN", maxLength = 50)
    private String name;

    @Schema(description = "Descripción del rol", example = "Administrador del sistema con acceso completo")
    private String description;

    @NotNull(message = "El estado activo es obligatorio")
    @Schema(description = "Indica si el rol está activo", example = "true")
    private Boolean active;
}
