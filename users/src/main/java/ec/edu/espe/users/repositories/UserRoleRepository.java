package ec.edu.espe.users.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.edu.espe.users.entities.UserRole;
import ec.edu.espe.users.entities.UserRoleId;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {

    List<UserRole> findByUserIdPerson(UUID idPerson);

    boolean existsByIdIdUserAndIdIdRole(UUID idUser, UUID idRole);
}
