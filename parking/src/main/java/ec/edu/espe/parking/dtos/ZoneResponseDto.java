package ec.edu.espe.parking.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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

    private ZoneType type;

    private int capacity;

    private boolean enabled;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<SpaceResponseDto> spaces;
}
