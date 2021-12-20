package enzoDevoto.apps.medicineTakeCare.web.service;

import enzoDevoto.apps.medicineTakeCare.web.model.PatientDto;

import java.util.UUID;

public interface PatientService {

    PatientDto getPatientById(UUID patientId);

    void updatePatient(UUID patientId, PatientDto patientDto);

    void deletePatient(UUID patientId);

    PatientDto saveNewPatient(PatientDto patientDto);
}
