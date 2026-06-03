package ec.edu.espe.parking.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.edu.espe.parking.entities.Space;

public interface SpaceRepository extends JpaRepository<Space, UUID> {
    Optional<Space> findById(UUID id);

    boolean existsByCode(String code);

    List<Space> findByZoneId(UUID zoneId);

    List<Space> findByZoneIdAndIsActive(UUID zoneId, boolean isActive);
}
