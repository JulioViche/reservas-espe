package ec.edu.espe.parking.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import ec.edu.espe.parking.entities.SpaceStatus;
import ec.edu.espe.parking.entities.SpaceType;
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

    private SpaceType type;

    private SpaceStatus status;

    private boolean enabled;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private UUID zoneId;

    private String zoneName;
}
