package ec.edu.espe.parking.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.edu.espe.parking.entities.Zone;

public interface ZoneRepository extends JpaRepository<Zone, UUID> {

    boolean existsByName(String name);

    List<Zone> findByCodeStartingWith(String prefix);
}