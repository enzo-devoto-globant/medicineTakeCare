package enzoDevoto.apps.medicineTakeCare.web.repository;

import enzoDevoto.apps.medicineTakeCare.web.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByEmail(String email);
    Optional<Patient> findByUsernameOrEmail(String username, String email);
    Optional<Patient> findByUsername(String username);
    Boolean existsByUsername (String username);
    Boolean existsByEmail(String email);
}
