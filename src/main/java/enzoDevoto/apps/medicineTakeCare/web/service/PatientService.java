package enzoDevoto.apps.medicineTakeCare.web.service;

import enzoDevoto.apps.medicineTakeCare.web.entity.Patient;
import enzoDevoto.apps.medicineTakeCare.web.model.PatientDto;
import enzoDevoto.apps.medicineTakeCare.web.model.PatientResponse;


public interface PatientService {

    PatientResponse getPatients(int pageNo, int pageSize, String sortBY, String sortDir);

    PatientResponse getPatientById(Long patientId);

    PatientDto updatePatient(Long patientId, PatientDto patientDto);

    void deletePatient(Long patientId);

    PatientDto saveNewPatient(PatientDto patientDto);
}
