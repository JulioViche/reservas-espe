package ec.edu.espe.users.dtos;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Solicitud para asignar o remover un rol de un usuario")
public class UserRoleRequestDto {

    @NotNull(message = "El ID del usuario es obligatorio")
    @Schema(description = "Identificador del usuario", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID idUser;

    @NotNull(message = "El ID del rol es obligatorio")
    @Schema(description = "Identificador del rol", example = "123e4567-e89b-12d3-a456-426614174001")
    private UUID idRole;
}
