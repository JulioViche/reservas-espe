package ec.edu.espe.users.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonResponseDto {

    private UUID id;

    private String dni;

    private String firstName;

    private String middleName;

    private String lastName;

    private String email;

    private String phone;

    private String address;

    private String nationality;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
