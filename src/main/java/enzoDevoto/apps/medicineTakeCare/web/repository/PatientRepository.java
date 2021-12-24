package enzoDevoto.apps.medicineTakeCare.web.repository;

import enzoDevoto.apps.medicineTakeCare.web.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
