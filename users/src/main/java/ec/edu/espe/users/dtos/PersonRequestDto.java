package ec.edu.espe.users.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
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
@Schema(description = "Solicitud para crear o actualizar una persona")
public class PersonRequestDto {

    @NotBlank(message = "El DNI es obligatorio")
    @Size(max = 30, message = "El DNI debe tener menos de 30 caracteres")
    @Schema(description = "Documento Nacional de Identidad", example = "1234567890", maxLength = 30)
    private String dni;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 30, message = "El nombre debe tener menos de 30 caracteres")
    @Schema(description = "Primer nombre", example = "Juan", maxLength = 30)
    private String firstName;

    @Size(max = 30, message = "El segundo nombre debe tener menos de 30 caracteres")
    @Schema(description = "Segundo nombre (opcional)", example = "Carlos", maxLength = 30)
    private String middleName;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 30, message = "El apellido debe tener menos de 30 caracteres")
    @Schema(description = "Apellido", example = "Pérez", maxLength = 30)
    private String lastName;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no es v\u00e1lido")
    @Size(max = 50, message = "El email debe tener menos de 50 caracteres")
    @Schema(description = "Correo electrónico", example = "juan.perez@email.com", maxLength = 50)
    private String email;

    @Size(max = 15, message = "El tel\u00e9fono debe tener menos de 15 caracteres")
    @Schema(description = "Número de teléfono", example = "+593 99 123 4567", maxLength = 15)
    private String phone;

    @Schema(description = "Dirección", example = "Av. Principal y Calle Secundaria")
    private String address;

    @Size(max = 30, message = "La nacionalidad debe tener menos de 30 caracteres")
    @Schema(description = "Nacionalidad", example = "Ecuatoriana", maxLength = 30)
    private String nationality;

    @NotNull(message = "El estado activo es obligatorio")
    @Schema(description = "Indica si la persona está activa", example = "true")
    private Boolean active;
}
