package ec.edu.espe.users.dtos;

import java.time.LocalDateTime;
import java.util.List;
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
@Schema(description = "Respuesta con datos de un usuario del sistema")
public class UserResponseDto {

    @Schema(description = "Identificador único del usuario (mismo que el de la persona)")
    private UUID idPerson;

    @Schema(description = "Nombre de usuario", example = "jperez")
    private String username;

    @Schema(description = "Indica si el usuario está activo", example = "true")
    private Boolean active;

    @Schema(description = "Fecha del último inicio de sesión")
    private LocalDateTime lastLogin;

    @Schema(description = "Fecha de creación")
    private LocalDateTime createdAt;

    @Schema(description = "Fecha de última actualización")
    private LocalDateTime updatedAt;

    @Schema(description = "Nombre de la persona asociada", example = "Juan")
    private String personFirstName;

    @Schema(description = "Apellido de la persona asociada", example = "Pérez")
    private String personLastName;

    @Schema(description = "Correo electrónico de la persona asociada", example = "juan.perez@email.com")
    private String personEmail;

    @Schema(description = "Lista de roles asignados al usuario", example = "[\"ADMIN\", \"USER\"]")
    private List<String> roles;
}
