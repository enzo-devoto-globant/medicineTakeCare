package enzoDevoto.apps.medicineTakeCare.web.repository;

import enzoDevoto.apps.medicineTakeCare.web.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);
}
