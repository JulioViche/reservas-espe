package ec.edu.espe.parking.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import ec.edu.espe.parking.entities.Space;
import ec.edu.espe.parking.entities.ZoneType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ZoneResponseDto {

    private UUID id;

    private String code;

    private String name;

    private String description;

    private boolean isActive;

    private ZoneType type;

    private List<Space> spaces;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
