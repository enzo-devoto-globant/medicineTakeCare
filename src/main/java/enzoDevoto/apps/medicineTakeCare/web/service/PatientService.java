package enzoDevoto.apps.medicineTakeCare.web.service;

import enzoDevoto.apps.medicineTakeCare.web.model.PatientDto;

import java.util.List;
import java.util.UUID;

public interface PatientService {
    List<PatientDto> getPatients();

    PatientDto getPatientById(Long patientId);

    PatientDto updatePatient(Long patientId, PatientDto patientDto);

    void deletePatient(Long patientId);

    PatientDto saveNewPatient(PatientDto patientDto);
}
