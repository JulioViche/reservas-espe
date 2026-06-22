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
@Schema(description = "Respuesta con datos de una persona")
public class PersonResponseDto {

    @Schema(description = "Identificador único de la persona")
    private UUID id;

    @Schema(description = "Documento Nacional de Identidad", example = "1234567890")
    private String dni;

    @Schema(description = "Primer nombre", example = "Juan")
    private String firstName;

    @Schema(description = "Segundo nombre", example = "Carlos")
    private String middleName;

    @Schema(description = "Apellido", example = "Pérez")
    private String lastName;

    @Schema(description = "Correo electrónico", example = "juan.perez@email.com")
    private String email;

    @Schema(description = "Número de teléfono", example = "+593 99 123 4567")
    private String phone;

    @Schema(description = "Dirección", example = "Av. Principal y Calle Secundaria")
    private String address;

    @Schema(description = "Nacionalidad", example = "Ecuatoriana")
    private String nationality;

    @Schema(description = "Indica si la persona está activa", example = "true")
    private Boolean active;

    @Schema(description = "Fecha de creación")
    private LocalDateTime createdAt;

    @Schema(description = "Fecha de última actualización")
    private LocalDateTime updatedAt;
}
