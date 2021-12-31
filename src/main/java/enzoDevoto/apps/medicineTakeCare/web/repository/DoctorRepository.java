package enzoDevoto.apps.medicineTakeCare.web.repository;

import enzoDevoto.apps.medicineTakeCare.web.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
