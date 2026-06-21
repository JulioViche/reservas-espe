package ec.edu.espe.users.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.edu.espe.users.entities.Person;

public interface PersonRepository extends JpaRepository<Person, UUID> {

    boolean existsByEmail(String email);
}
