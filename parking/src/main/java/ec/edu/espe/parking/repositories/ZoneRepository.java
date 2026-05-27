package ec.edu.espe.parking.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.edu.espe.parking.entities.Zone;

public interface ZoneRepository extends JpaRepository<Zone, UUID> {

    boolean existsByCode(String code);

    boolean existsByName(String name);

    long countByCodeStartingWith(String prefix);
}