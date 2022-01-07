package enzoDevoto.apps.medicineTakeCare.web.repository;

import enzoDevoto.apps.medicineTakeCare.web.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByEmail(String email);
    Optional<Doctor> findByUsernameOrEmail(String username, String email);
    Optional<Doctor> findByUsername(String username);
    Boolean existsByUsername (String username);
    Boolean existsByEmail(String email);
}
