package ec.edu.espe.parking.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import ec.edu.espe.parking.entities.SpaceType;
import ec.edu.espe.parking.entities.Zone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpaceResponseDto {

    private UUID id;

    private String code;

    private String description;

    private boolean isOccupied;

    private SpaceType type;

    private Zone zone;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
