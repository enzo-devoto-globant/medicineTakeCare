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
                .age(30)//Integer.parseInt(PatientDto.Fields.age))
                .email("myEmail@goglaaa.clo")//PatientDto.Fields.email)
                .gender("Male")//PatientDto.Fields.gender)
                .name("Enzo Devoto")
                .timeOfEvaluation(Date.from(Instant.now()))
                .isCritical(false)
                .description("This is the dummy patient he comes and goes all the time, loves to suffer.")//PatientDto.Fields.name)
                .build();


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