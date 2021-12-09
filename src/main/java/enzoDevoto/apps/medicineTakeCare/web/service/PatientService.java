package enzoDevoto.apps.medicineTakeCare.web.service;

import enzoDevoto.apps.medicineTakeCare.web.model.PatientDto;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface PatientService {
    Map<String,PatientDto> getPatientById(UUID patientId);

    PatientDto setNewDto(PatientDto patientDto);

    void updatePatient(UUID patientId, PatientDto patientDto);

    void deletePatient(UUID patientId);
}
