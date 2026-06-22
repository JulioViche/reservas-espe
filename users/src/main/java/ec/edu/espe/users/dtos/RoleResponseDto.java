package ec.edu.espe.users.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Respuesta con datos de un rol")
public class RoleResponseDto {

    @Schema(description = "Identificador único del rol")
    private UUID id;

    @Schema(description = "Nombre del rol", example = "ADMIN")
    private String name;

    @Schema(description = "Descripción del rol", example = "Administrador del sistema con acceso completo")
    private String description;

    @Schema(description = "Indica si el rol está activo", example = "true")
    private Boolean active;

    @Schema(description = "Fecha de creación")
    private LocalDateTime createdAt;

    @Schema(description = "Fecha de última actualización")
    private LocalDateTime updatedAt;
}
