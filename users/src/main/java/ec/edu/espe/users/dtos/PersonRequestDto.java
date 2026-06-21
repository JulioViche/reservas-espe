package ec.edu.espe.users.dtos;

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
public class PersonRequestDto {

    @NotBlank(message = "El DNI es obligatorio")
    @Size(max = 30, message = "El DNI debe tener menos de 30 caracteres")
    private String dni;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 30, message = "El nombre debe tener menos de 30 caracteres")
    private String firstName;

    @Size(max = 30, message = "El segundo nombre debe tener menos de 30 caracteres")
    private String middleName;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 30, message = "El apellido debe tener menos de 30 caracteres")
    private String lastName;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no es válido")
    @Size(max = 50, message = "El email debe tener menos de 50 caracteres")
    private String email;

    @Size(max = 15, message = "El teléfono debe tener menos de 15 caracteres")
    private String phone;

    private String address;

    @Size(max = 30, message = "La nacionalidad debe tener menos de 30 caracteres")
    private String nationality;

    @NotNull(message = "El estado activo es obligatorio")
    private Boolean active;
}
