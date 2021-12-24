package enzoDevoto.apps.medicineTakeCare.web.service;

import enzoDevoto.apps.medicineTakeCare.web.model.PatientDto;
import enzoDevoto.apps.medicineTakeCare.web.model.PatientResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PatientService {

    PatientResponse getPatients(int pageNo, int pageSize);

    ResponseEntity<PatientDto> getPatientById(Long patientId);

    PatientDto updatePatient(Long patientId, PatientDto patientDto);

    void deletePatient(Long patientId);

    PatientDto saveNewPatient(PatientDto patientDto);
}
