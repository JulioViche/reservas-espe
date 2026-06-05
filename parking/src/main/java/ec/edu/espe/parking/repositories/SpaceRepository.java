package ec.edu.espe.parking.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.edu.espe.parking.entities.Space;
import ec.edu.espe.parking.entities.SpaceStatus;

public interface SpaceRepository extends JpaRepository<Space, UUID> {

    List<Space> findByZoneId(UUID zoneId);

    List<Space> findByCodeStartingWith(String prefix);

    long countByZoneIdAndStatus(UUID zoneId, SpaceStatus status);
}
