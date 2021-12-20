package enzoDevoto.apps.medicineTakeCare.web.service;

import enzoDevoto.apps.medicineTakeCare.web.model.PatientDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class PatientServiceImpl implements PatientService {

    @Override
    public PatientDto getPatientById(UUID patientId) {
        log.info("getting patient data: ");

        return PatientDto.builder()
                .id(UUID.randomUUID())//UUID.fromString(PatientDto.Fields.id)
                .age(19)//Integer.parseInt(PatientDto.Fields.age))
                .email("stefanoDevoto@gmail.com")//PatientDto.Fields.email)
                .gender("Male")//PatientDto.Fields.gender)
                .name("Stefano Devoto")
                .timeOfEvaluation(Date.from(Instant.now()))
                .isCritical(false)
                .description("some dummy patient")//PatientDto.Fields.name)
                .build();
    }


    @Override
    public void updatePatient(UUID patientId, PatientDto patientDto) {

    }

    @Override
    public void deletePatient(UUID patientId) {
        log.debug("Deleting a patient");
    }

    @Override
    public PatientDto saveNewPatient(PatientDto patientDto) {
        log.info("Creating patient data: ");
        return PatientDto.builder()
                .id(UUID.randomUUID())
                .build();
    }


}
