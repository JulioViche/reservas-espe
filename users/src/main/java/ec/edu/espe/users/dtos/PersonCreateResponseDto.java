package ec.edu.espe.users.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonCreateResponseDto {

    private PersonResponseDto person;

    private String username;

    private String generatedPassword;
}
