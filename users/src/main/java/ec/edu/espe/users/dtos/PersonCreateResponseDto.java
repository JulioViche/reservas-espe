package ec.edu.espe.users.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Respuesta al crear una persona con su usuario y contraseña generados")
public class PersonCreateResponseDto {

    @Schema(description = "Datos de la persona creada")
    private PersonResponseDto person;

    @Schema(description = "Nombre de usuario generado", example = "jcperez")
    private String username;

    @Schema(description = "Contraseña generada automáticamente (14 caracteres alfanuméricos con caracteres especiales)", example = "Abc123!xyz$9mK")
    private String generatedPassword;
}
