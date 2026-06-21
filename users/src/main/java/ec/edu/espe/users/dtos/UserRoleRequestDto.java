package ec.edu.espe.users.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleRequestDto {

    @NotNull(message = "El ID del usuario es obligatorio")
    private UUID idUser;

    @NotNull(message = "El ID del rol es obligatorio")
    private UUID idRole;
}
