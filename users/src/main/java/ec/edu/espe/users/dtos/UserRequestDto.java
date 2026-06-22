package ec.edu.espe.users.dtos;

import java.util.UUID;

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
@Schema(description = "Solicitud para crear un usuario (no expuesto actualmente en API)")
public class UserRequestDto {

    @NotBlank(message = "El username es obligatorio")
    @Size(min = 3, max = 15, message = "El username debe tener entre 3 y 15 caracteres")
    @Schema(description = "Nombre de usuario", example = "jperez", minLength = 3, maxLength = 15)
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    @Schema(description = "Contraseña del usuario", example = "securePassword123")
    private String password;

    @NotNull(message = "El ID de la persona es obligatorio")
    @Schema(description = "Identificador de la persona asociada", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID idPerson;
}
