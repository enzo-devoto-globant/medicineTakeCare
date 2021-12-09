package enzoDevoto.apps.medicineTakeCare.web.service;

import enzoDevoto.apps.medicineTakeCare.MedicineTakeCareApplication;
import enzoDevoto.apps.medicineTakeCare.web.model.PatientDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class PatientServiceImpl implements PatientService {
    @Override
    public Map<String,PatientDto> getPatientById(UUID patientId) {
        Map<String, PatientDto> patientsList = new HashMap<>();
        log.info("getting patient data: ");


        PatientDto myNewPatient = PatientDto.builder()
                .id(UUID.randomUUID())//UUID.fromString(PatientDto.Fields.id)
                .age(19)//Integer.parseInt(PatientDto.Fields.age))
                .email("stefanoDevoto@gmail.com")//PatientDto.Fields.email)
                .gender("Male")//PatientDto.Fields.gender)
                .name("Stefano Devoto")
                .timeOfEvaluation(Date.from(Instant.now()))
                .isCritical(false)
                .description("Stefano dejo la motita.")//PatientDto.Fields.name)
                .build();

        PatientDto myNewPatient2 = PatientDto.builder()
                .id(UUID.randomUUID())//UUID.fromString(PatientDto.Fields.id)
                .age(19)//Integer.parseInt(PatientDto.Fields.age))
                .email("stefanoDevoto@gmail.com")//PatientDto.Fields.email)
                .gender("Male")//PatientDto.Fields.gender)
                .name("Stefano Devoto")
                .timeOfEvaluation(Date.from(Instant.now()))
                .isCritical(false)
                .description("Stefano dejo la motita.")//PatientDto.Fields.name)
                .build();

        patientsList.put("Patient:", myNewPatient);
        patientsList.put("second Patient: ", myNewPatient2);

        return patientsList;

    }

    @Override
    public PatientDto setNewDto(PatientDto patientDto) {
        log.info("Creating patient data: ");
        return PatientDto.builder()
                .id(UUID.randomUUID())
                .build();
    }

    @Override
    public void updatePatient(UUID patientId, PatientDto patientDto) {

    }

    @Override
    public void deletePatient(UUID patientId) {
        log.debug("Deleting a patient");
    }


}
